package ink.zxu.learn_japanese.controller;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.CommunicationService;
import ink.zxu.learn_japanese.service.UploadService;
import ink.zxu.learn_japanese.utils.BaseController;
import ink.zxu.learn_japanese.utils.PageData;
import ink.zxu.learn_japanese.utils.SessionManager;
import ink.zxu.learn_japanese.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author 张伟
 * @date 2019/9/22 11:28
 */
@Controller
@RequestMapping(value = "/communication", produces = "text/html;charset=UTF-8")
public class CommunicationController extends BaseController {

    @Autowired
    private CommunicationService communicationService;

    @Autowired
    private UploadService uploadService;


    @ResponseBody
    @RequestMapping("/showdongtai")
    public String showDongTai() throws Exception {
        List<PageData> pageDataList = null;
        PageData pd = this.getPageData();
        HttpSession session = SessionManager.getSession(pd.getString("token"));
        String user_id = (String) session.getAttribute("openid");
        pd.put("user_id", user_id);
        pd.put("status", "1");
        Integer zan_num;
        Integer reply_num;
        Boolean is_zan;
        pageDataList = communicationService.showDongtai();
        for (int i = 0; i < pageDataList.size(); i++) {
            pd.put("dt_id", pageDataList.get(i).getString("id"));
            pd.put("item_id", pageDataList.get(i).getString("id"));
            zan_num = communicationService.queryZanNum(pd);//当前动态点赞数量
            reply_num = communicationService.queryCommentNumByDtId(pd);//当前动态评论数量
            is_zan = (communicationService.queryIsZan(pd) == null ? false : true);//我是否对当前动态点赞
            pageDataList.get(i).put("zan_num", zan_num);
            pageDataList.get(i).put("reply_num", reply_num);
            pageDataList.get(i).put("is_zan", is_zan);
        }
        return new Gson().toJson(pageDataList);
    }


    @Transactional
    @ResponseBody
    @RequestMapping("/deleteDt")
    public String deleteDt() {
        PageData pageData = this.getPageData();
        Map<String, String> resultMap = new HashMap<>();
        try {
            communicationService.deleteContent(pageData);
            communicationService.deleteComment(pageData);
            communicationService.deleteReply(pageData);
            resultMap.put("message", "Success");
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            System.out.println("回滚事务!");
            resultMap.put("message", "fail");
        }
        return new Gson().toJson(resultMap);
    }


    @Transactional
    @ResponseBody
    @RequestMapping("/addcontent")
    public String addContent() {
        PageData pageData = this.getPageData();
        Map<String, String> resultMap = new HashMap<>();
        String images = "";
        try {
            HttpSession session = SessionManager.getSession(pageData.getString("token"));
            String openid = (String) session.getAttribute("openid");
            pageData.put("id", UUIDUtil.getUid());
            pageData.put("openid", openid);
            communicationService.addContent(pageData);
            resultMap.put("message", "Success");
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            System.out.println("回滚事务!");
            resultMap.put("message", "fail");
        }
        return new Gson().toJson(resultMap);
    }


    @ResponseBody
    @RequestMapping("/addimage")
    public String addImage(@RequestParam("file") MultipartFile file) {
        PageData resultMap = new PageData();
        if (file.isEmpty()) {
            resultMap.put("message", "file is null");
        } else {
            try {
                String url = uploadService.imageUpload(file);
                resultMap.put("message", "Success");
                resultMap.put("url", url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Gson().toJson(resultMap);
    }


    //查询评论
    @ResponseBody
    @RequestMapping("/queryComment")
    public String queryComment() {
        PageData pd = this.getPageData();
        PageData result = new PageData();
        List<PageData> resultList = new ArrayList<>();
        System.out.println(pd.getString("dt_id"));
        try {
            String token = pd.getString("token");
            HttpSession session = SessionManager.getSession(token);
            if (session != null) {
                pd.put("user_id", session.getAttribute("openid"));
                pd.put("status", 1);
                pd.put("item_id", pd.getString("dt_id"));
                Integer zan_num = communicationService.queryZanNum(pd);//当前文章点赞数量
                Integer reply_num = communicationService.queryCommentNumByDtId(pd);//当前动态回复数量
                Boolean is_zan = (communicationService.queryIsZan(pd) == null ? false : true);//我是否对当前动态点赞
                resultList = communicationService.queryComment(pd);//当前文章的评论列表
                for (int i = 0; i < resultList.size(); i++) {
                    List<PageData> replyList = new ArrayList<>();
                    pd.put("comment_id", resultList.get(i).getString("comment_id"));//每个评论的id
                    pd.put("item_id", resultList.get(i).getString("comment_id"));//每个评论的id
                    Integer zan_num2 = communicationService.queryZanNum(pd);//当前评论的点赞数量
                    Integer reply_num2 = communicationService.queryReplyNum(pd);//当前评论的回复数量
                    Boolean is_zan2 = (communicationService.queryIsZan(pd) == null ? false : true);//我是否对当前评论点赞
                    resultList.get(i).put("zan_num", zan_num2);//点赞数
                    resultList.get(i).put("reply_num", reply_num2);//回复数
                    resultList.get(i).put("is_zan", is_zan2);//我是否点赞
                    PageHelper.startPage(1, 3);
                    replyList = communicationService.queryReply(pd);//当前评论的回复列表 默认只是展示三条 点击详情后查看全部
                    for (int j = 0; j < replyList.size(); j++) {//遍历回复列表 查询点赞信息
                        pd.put("item_id", replyList.get(j).getString("reply_id"));//当前的回复id
                        Integer zan_num3 = communicationService.queryZanNum(pd);
                        Boolean is_zan3 = (communicationService.queryIsZan(pd) == null ? false : true);
                        replyList.get(j).put("zan_num", zan_num3);
                        replyList.get(j).put("is_zan", is_zan3);
                    }
                    resultList.get(i).put("replyList", replyList);
                }
                result.put("datas", resultList);
                result.put("message", "success");
                result.put("zan_num", zan_num);
                result.put("reply_num", reply_num);
                result.put("is_zan", is_zan);
            } else {
                result.put("message", "relogin");
            }
        } catch (Exception e) {
            result.put("message", "fail");
        }
        return new Gson().toJson(result);
    }


    //点赞
    @Transactional
    @ResponseBody
    @RequestMapping("/addZan")
    public String addZan() {
        PageData pd = this.getPageData();
        PageData result = new PageData();
        Integer num = 0;
        Integer zan_num = 0;
        try {
            String token = pd.getString("token");
            HttpSession session = SessionManager.getSession(token);
            if (session != null) {
                pd.put("user_id", session.getAttribute("openid"));
//                String type=pd.getString("type");
                Integer isDelete = 1;
//                if(type.equals("1")){//点赞类型是文章 默认不被删除
//                    isDelete= 1;
//                }else if(type.equals("2")){//点赞类型是评论
//                    isDelete= communicationService.queryCommentNumById(pd);
//                }else if(type.equals("3")){//点赞类型是回复
//                    isDelete= communicationService.queryReplyNumById(pd);
//                }
                if (isDelete > 0) {//查询之前看该评论有没有被删除
                    PageData zanData = communicationService.queryIsZan(pd);
                    if (zanData == null) {//第一次点赞
                        num = communicationService.addZan(pd);
                        if (num > 0) {
                            result.put("message", "is_zan");
                        } else {
                            result.put("message", "fail");
                        }
                    } else {//修改点赞信息
                        String status = zanData.getString("status");//获取之前的点赞记录
                        if (status.equals("1")) {//如果之前是点过赞 则取消点赞
                            pd.put("status", "0");
                            num = communicationService.updateZan(pd);
                            if (num > 0) {//取消点赞成功
                                result.put("message", "no_zan");
                            } else {//取消点赞失败
                                result.put("message", "fail");
                            }
                        } else {//更改为有效赞
                            pd.put("status", "1");
                            num = communicationService.updateZan(pd);
                            if (num > 0) {//更新成功
                                result.put("message", "is_zan");
                            } else {//更新失败
                                result.put("message", "fail");
                            }
                        }
                    }
                    zan_num = communicationService.queryZanNum(pd);
                    result.put("zan_num", zan_num);
                } else {
                    result.put("message", "had_delete");
                }
            } else {
                result.put("message", "relogin");
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("回滚事务!");
            result.put("message", "fail");
        }
        return new Gson().toJson(result);
    }


    //评论动态
    @ResponseBody
    @RequestMapping("/addComment")
    public String addComment() {
        PageData pd = this.getPageData();
        PageData result = new PageData();
        System.out.println(pd.getString("content"));
        try {
            String token = pd.getString("token");
            HttpSession session = SessionManager.getSession(token);
            if (session != null) {
                pd.put("comment_id", UUIDUtil.getUid());
                pd.put("user_id", session.getAttribute("openid"));
                Integer num = communicationService.addComment(pd);
                if (num > 0) {
                    result.put("message", "success");
                } else {
                    result.put("message", "fail");
                }
            } else {
                result.put("message", "relogin");
            }
        } catch (Exception e) {
            result.put("message", "fail");
        }
        return new Gson().toJson(result);
    }


    //查询回复列表
    @Transactional
    @ResponseBody
    @RequestMapping("/queryReply")
    public String queryReply() {
        PageData pd = this.getPageData();
        List<PageData> replyList = null;
        PageData result = new PageData();
        try {
            String token = pd.getString("token");
            HttpSession session = SessionManager.getSession(token);
            if (session != null) {
                pd.put("user_id", session.getAttribute("openid"));
                pd.put("item_id", pd.getString("comment_id"));
                pd.put("status", "1");
                if (communicationService.queryCommentNumById(pd) > 0) {
                    Integer reply_num = communicationService.queryReplyNum(pd);
                    Integer zanNum = communicationService.queryZanNum(pd);
                    Boolean is_zan = (communicationService.queryIsZan(pd) == null ? false : true);
                    replyList = communicationService.queryReply(pd);//查询回复列表
                    for (int i = 0; i < replyList.size(); i++) {
                        pd.put("item_id", replyList.get(i).getString("reply_id"));
                        Integer zan_num = communicationService.queryZanNum(pd);
                        replyList.get(i).put("zan_num", zan_num);
                        if (communicationService.queryIsZan(pd) == null) {
                            replyList.get(i).put("is_zan", false);
                        } else {
                            replyList.get(i).put("is_zan", true);
                        }
                    }
                    result.put("datas", replyList);
                    result.put("reply_num", reply_num);
                    result.put("zanNum", zanNum);
                    result.put("is_zan", is_zan);
                } else {
                    result.put("message", "comment is delete");
                }

            } else {
                result.put("message", "relogin");
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            System.out.println("回滚事务!");
            result.put("message", "fail");
        }
        return new Gson().toJson(result);
    }

    //回复评论
    @ResponseBody
    @RequestMapping("/addReply")
    public String addReply() {
        PageData pd = this.getPageData();
        PageData result = new PageData();
        System.out.println(pd.getString("content"));
        try {
            String token = pd.getString("token");
            HttpSession session = SessionManager.getSession(token);
            if (session != null) {
                pd.put("reply_id", UUIDUtil.getUid());
                pd.put("from_userid", session.getAttribute("openid"));
                Integer num = communicationService.addReply(pd);
                if (num > 0) {
                    result.put("message", "success");
                } else {
                    result.put("message", "fail");
                }
            } else {
                result.put("message", "relogin");
            }
        } catch (Exception e) {
            result.put("message", "fail");
        }
        return new Gson().toJson(result);
    }


}
