package ink.zxu.learn_japanese.controller;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.KnowService;
import ink.zxu.learn_japanese.utils.BaseController;
import ink.zxu.learn_japanese.utils.PageData;
import ink.zxu.learn_japanese.utils.SessionManager;
import ink.zxu.learn_japanese.utils.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zhangwei
 * @date 2020/2/9 14:52
 */
@Controller
@RequestMapping(value = "/knowController", produces = "text/html;charset=UTF-8")
public class KnowledgeController extends BaseController {
    @Resource
    private KnowService knowService;

    //查询类型
    @ResponseBody
    @RequestMapping("/queryType")
    public String queryType() throws Exception {
        PageData pd = this.getPageData();
        String token = pd.getString("token");
        HttpSession session = SessionManager.getSession(token);
        pd.put("user_id", session.getAttribute("openid"));
        pd.put("status", "1");
        List<PageData> list = knowService.queryTypeList(pd);
        for (int i = 0; i < list.size(); i++) {
            pd.put("type_id", list.get(i).getString("type_id"));
            List<PageData> know_list = new ArrayList<>();
            know_list = knowService.queryKnowByID(pd);
            for (int j = 0; j < know_list.size(); j++) {
                pd.put("item_id", know_list.get(j).getString("know_id"));
                pd.put("article_id", know_list.get(j).getString("know_id"));
                Integer reply_num = knowService.queryCommentNumByArtId(pd);
                Integer zanNum = knowService.queryZanNum(pd);
                Boolean is_zan = (knowService.queryIsZan(pd) == null ? false : true);
                know_list.get(j).put("reply_num", reply_num);
                know_list.get(j).put("zanNum", zanNum);
                know_list.get(j).put("is_zan", is_zan);
            }
            list.get(i).put("type_count", know_list.size());
            list.get(i).put("know_list", know_list);
        }
        System.out.println(list);
        return new Gson().toJson(list);
    }


    //查询知识点
    @ResponseBody
    @RequestMapping("/queryKnow")
    public String queryKnow() throws Exception {
        PageData pd = this.getPageData();
        List<PageData> list = knowService.queryKnowList(pd);
        System.out.println(list.get(0));
        return new Gson().toJson(list.get(0));
    }


    //查询回复列表
    @Transactional
    @ResponseBody
    @RequestMapping("/queryReply")
    public String queryReply() throws Exception {
        PageData pd = this.getPageData();
        List<PageData> replyList = null;
        PageData result = new PageData();
        try {
            String token = pd.getString("token");
            HttpSession session = SessionManager.getSession(token);
            if (session != null) {
                pd.put("user_id", session.getAttribute("openid"));
                pd.put("status", "1");
                if (knowService.queryCommentNumById(pd) > 0) {
                    Integer reply_num = knowService.queryReplyNum(pd);
                    Integer zanNum = knowService.queryZanNum(pd);
                    Boolean is_zan = (knowService.queryIsZan(pd) == null ? false : true);
                    replyList = knowService.queryReply(pd);//查询回复列表
                    for (int i = 0; i < replyList.size(); i++) {
                        pd.put("item_id", replyList.get(i).getString("reply_id"));
                        Integer zan_num = knowService.queryZanNum(pd);
                        replyList.get(i).put("zan_num", zan_num);
                        if (knowService.queryIsZan(pd) == null) {
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


    //评论文章
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
                Integer num = knowService.addComment(pd);
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

    //查询评论
    @ResponseBody
    @RequestMapping("/queryComment")
    public String queryComment() {
        PageData pd = this.getPageData();
        PageData result = new PageData();
        List<PageData> resultList = new ArrayList<>();
        System.out.println(pd.getString("article_id"));
        try {
            String token = pd.getString("token");
            HttpSession session = SessionManager.getSession(token);
            if (session != null) {
                pd.put("user_id", session.getAttribute("openid"));
                pd.put("status", 1);
                Integer zan_num = knowService.queryZanNum(pd);//当前文章点赞数量
                Integer reply_num = knowService.queryCommentNumByArtId(pd);//当前文章回复数量
                Boolean is_zan = (knowService.queryIsZan(pd) == null ? false : true);//我是否对当前文章点赞
                resultList = knowService.queryComment(pd);//当前文章的评论列表
                for (int i = 0; i < resultList.size(); i++) {
                    List<PageData> replyList = new ArrayList<>();
                    pd.put("comment_id", resultList.get(i).getString("comment_id"));//每个评论的id
                    pd.put("item_id", resultList.get(i).getString("comment_id"));//每个评论的id
                    Integer zan_num2 = knowService.queryZanNum(pd);//当前评论的点赞数量
                    Integer reply_num2 = knowService.queryReplyNum(pd);//当前评论的回复数量
                    Boolean is_zan2 = (knowService.queryIsZan(pd) == null ? false : true);//我是否对当前评论点赞
                    resultList.get(i).put("zan_num", zan_num2);//点赞数
                    resultList.get(i).put("reply_num", reply_num2);//回复数
                    resultList.get(i).put("is_zan", is_zan2);//我是否点赞
                    PageHelper.startPage(1, 3);
                    replyList = knowService.queryReply(pd);//当前评论的回复列表 默认只是展示三条 点击详情后查看全部
                    for (int j = 0; j < replyList.size(); j++) {//遍历回复列表 查询点赞信息
                        pd.put("item_id", replyList.get(j).getString("reply_id"));//当前的回复id
                        Integer zan_num3 = knowService.queryZanNum(pd);
                        Integer reply_num3 = knowService.queryReplyNum(pd);
                        Boolean is_zan3 = (knowService.queryIsZan(pd) == null ? false : true);
                        replyList.get(j).put("zan_num", zan_num3);
                        replyList.get(j).put("reply_num", reply_num3);
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


    //回复评论
    @ResponseBody
    @RequestMapping("/addReply")
    public String addReply() throws Exception {
        PageData pd = this.getPageData();
        PageData result = new PageData();
        System.out.println(pd.getString("content"));
        try {
            String token = pd.getString("token");
            HttpSession session = SessionManager.getSession(token);
            if (session != null) {
                pd.put("reply_id", UUIDUtil.getUid());
                pd.put("from_userid", session.getAttribute("openid"));
                Integer num = knowService.addReply(pd);
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
                String type = pd.getString("type");
                Integer isDelete = 0;
                if (type.equals("1")) {//点赞类型是文章 默认不被删除
                    isDelete = 1;
                } else if (type.equals("2")) {//点赞类型是评论
                    isDelete = knowService.queryCommentNumById(pd);
                } else if (type.equals("3")) {//点赞类型是回复
                    isDelete = knowService.queryReplyNumById(pd);
                }
                if (isDelete > 0) {//查询之前看该评论有没有被删除
                    PageData zanData = knowService.queryIsZan(pd);
                    if (zanData == null) {//第一次点赞
                        num = knowService.addZan(pd);
                        if (num > 0) {
                            result.put("message", "is_zan");
                        } else {
                            result.put("message", "fail");
                        }
                    } else {//修改点赞信息
                        String status = zanData.getString("status");//获取之前的点赞记录
                        if (status.equals("1")) {//如果之前是点过赞 则取消点赞
                            pd.put("status", "0");
                            num = knowService.updateZan(pd);
                            if (num > 0) {//取消点赞成功
                                result.put("message", "no_zan");
                            } else {//取消点赞失败
                                result.put("message", "fail");
                            }
                        } else {//更改为有效赞
                            pd.put("status", "1");
                            num = knowService.updateZan(pd);
                            if (num > 0) {//更新成功
                                result.put("message", "is_zan");
                            } else {//更新失败
                                result.put("message", "fail");
                            }
                        }
                    }
                    zan_num = knowService.queryZanNum(pd);
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

}
