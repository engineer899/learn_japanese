package ink.zxu.learn_japanese.controller;

import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.VideoService;
import ink.zxu.learn_japanese.utils.BaseController;
import ink.zxu.learn_japanese.utils.PageData;
import ink.zxu.learn_japanese.utils.SessionManager;
import ink.zxu.learn_japanese.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 张伟
 * @date 2019/10/16 22:35
 */
@Controller
@RequestMapping(value="/video",produces = "text/html;charset=UTF-8")
public class VideoController extends BaseController {
    @Autowired
    private VideoService videoService;
    private static String uuid;

    @RequestMapping(value = "/upload_view")
    public ModelAndView upload() {
        PageData pd = this.getPageData();
        ModelAndView mv = this.getModelAndView();
        mv.addObject("pd", pd);
        mv.setViewName("pages/video/upload");
        return mv;
    }



    @RequestMapping(value="/countByType")
    @ResponseBody
    public String countByType(){
        List<Map<String,String>> resultList;
        resultList=videoService.countByType();
        return new Gson().toJson(resultList);
    }

    @RequestMapping(value="/showByType")
    @ResponseBody
    public String showByType() throws Exception {
        PageData pageData=this.getPageData();
        List<Map<String,String>> resultList;
        resultList=videoService.showByType(Integer.parseInt(pageData.getString("video_type")));
        return new Gson().toJson(resultList);
    }

    /**
     * 查看视频下方的所有应有的信息
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/showAllContentById")
    public String showComment() throws Exception {
        PageData pageData=this.getPageData();
        List<PageData> pageDataList=null;
        pageDataList=videoService.showAllContentById(pageData);
        for(String key:(Set<String>)pageData.keySet()){
            System.out.println(key+":"+pageData.getString(key));
        }
        return new Gson().toJson(pageDataList);
    }


    /**
     * 评论赞
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/zan")
    public String addVideoContentZan() throws Exception {
        PageData pageData=this.getPageData();
        Map<String,Object> resultMap=new HashMap<>();
        String token=pageData.getString("token");
        resultMap = videoService.zan(pageData);
        return  new Gson().toJson(resultMap);
    }

    /**
     * 回复赞
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/replyZan")
    public String addVideoContentReplyZan() throws Exception {
        PageData pageData=this.getPageData();
        Map<String,Object> resultMap=new HashMap<>();
        String token=pageData.getString("token");
        resultMap = videoService.replyZan(pageData);
        return  new Gson().toJson(resultMap);
    }

    /**
     * 发表新评论
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/addVideoContent")
    public String addVideoContent() throws Exception {
        PageData pageData=this.getPageData();
        Map<String,String> resultMap=new HashMap<>();
        String token=pageData.getString("token");
        HttpSession session=SessionManager.getSession(token);
        String openid=(String)session.getAttribute("openid");
        uuid= UUIDUtil.getUid();
        pageData.put("id",uuid);
        pageData.put("openid",openid);
        videoService.addVideoContent(pageData);
        for(String key:(Set<String>)pageData.keySet()){
            System.out.println(key+":"+pageData.getString(key));
        }
        resultMap.put("message","Success");
        return  new Gson().toJson(resultMap);
    }

    /**
     * 对视频评论回复
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/addVideoContentReply")
    public String addVideoContentReply() throws Exception {
        PageData pageData=this.getPageData();
        Map<String,String> resultMap=new HashMap<>();
        String token=pageData.getString("token");
        HttpSession session=SessionManager.getSession(token);
        String openid=(String)session.getAttribute("openid");
        uuid= UUIDUtil.getUid();
        pageData.put("replyid",uuid);
        pageData.put("openid",openid);
        int i = videoService.addVideoContentReply(pageData);
        for(String key:(Set<String>)pageData.keySet()){
            System.out.println("这是收到的数据:  "+key+":"+pageData.getString(key));
        }
        if(i==2){
            resultMap.put("message","Success");
            return  new Gson().toJson(resultMap);
        }else {
            resultMap.put("message","fail");
            return  new Gson().toJson(resultMap);
        }
    }

    /**
     * 展示某个评论的所有回复
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/showAllContentReply")
    public String showAllContentReply() throws Exception {
        PageData pageData=this.getPageData();
        List<PageData> pageDataList=null;
        pageDataList=videoService.showAllContentReply(pageData);
        for(String key:(Set<String>)pageData.keySet()){
            System.out.println(key+":"+pageData.getString(key));
        }
        return new Gson().toJson(pageDataList);

    }

    /**
     * 用户删除评论
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/deleteContentById")
    public String deleteContentById() throws Exception {
        PageData pageData=this.getPageData();
        Map<String,String> resultMap=new HashMap<>();
        videoService.deleteContentById(pageData);
        resultMap.put("message","Success");
        return  new Gson().toJson(resultMap);
    }

    /**
     *用户删除回复
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/deleteOneReplyById")
    public String deleteOneReplyById() throws Exception {
        PageData pageData=this.getPageData();
        Map<String,String> resultMap=new HashMap<>();
        videoService.deleteOneReplyById(pageData);
        resultMap.put("message","Success");
        return  new Gson().toJson(resultMap);
    }
}
