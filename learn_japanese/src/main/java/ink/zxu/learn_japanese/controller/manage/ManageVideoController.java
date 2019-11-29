package ink.zxu.learn_japanese.controller.manage;

import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.UploadService;
import ink.zxu.learn_japanese.utils.Page;
import ink.zxu.learn_japanese.service.VideoService;
import ink.zxu.learn_japanese.utils.BaseController;
import ink.zxu.learn_japanese.utils.PageData;
import ink.zxu.learn_japanese.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangwei
 * @date 2019/11/19 22:34
 */
@Controller
@RequestMapping(value = "/manage/manageVideoController",produces = "text/html;charset=UTF-8")
public class ManageVideoController extends BaseController {
    @Resource
    private VideoService videoService;

    @Autowired
    private UploadService uploadService;


    /**
     * 跳转到视频列表视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/video_list")
    public ModelAndView video_list(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("video/video_list");
        return mv;
    }

    /**
     * 跳转到视频添加视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/video_add")
    public ModelAndView addVideo(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("video/video_add");
        return mv;
    }


    /**
     * 跳转到视频编辑
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/video_update")
    public ModelAndView updateVideo() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        PageData result=videoService.queryVideoById(pd);
        // 设置返回视图和配置项数据
        mv.addObject("result",result);
        mv.setViewName("video/video_update");
        return mv;
    }


    /**
     * 跳转到视频详情
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/video_details")
    public ModelAndView queryVideoDetails() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        PageData result=videoService.queryVideoById(pd);
        // 设置返回视图和配置项数据
        mv.addObject("result", result);
        mv.setViewName("video/video_details");
        return mv;
    }

    /**
     *视频信息列表查询
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryVideoInfoJson", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryLawInfoJson(Page page) throws Exception {
        PageData pd = this.getPageData();
        // 设置分页
        page.setPd(pd);
        // 获取所有数据
        List<PageData> list = videoService.queryVideoInfoListPage(page);
        PageData userInfo = new PageData();
        // 设置返回数据和视图
        userInfo.put("code", 0);
        userInfo.put("count", page.getTotalResult());
        userInfo.put("data", list);
        userInfo.put("msg", "");
        return new Gson().toJson(userInfo);
    }


    /**
     * 添加视频
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addVideo")
    @ResponseBody
    public String addVideo(MultipartHttpServletRequest Request) throws Exception {
        Map<String,String> resultMap=new HashMap<>();
        List<MultipartFile> files = Request.getFiles("file");
        MultipartFile file = null;
        for (int i = 0; i < files.size(); ++i) {
            System.out.println("进入"+files.size());
            file=files.get(i);
            if(file.isEmpty()){
                resultMap.put("message","file is null");
            }else{
                try {
                    PageData pageData=this.getPageData();
                    //控制台输出 便于测试
                    for(String key:(Set<String>)pageData.keySet()){
                        System.out.println(key+":"+pageData.getString(key));
                    }
                    String video_url=uploadService.videoUpload(file);
                    pageData.put("id", UUIDUtil.getUid());
                    pageData.put("video_url",video_url);
                    pageData.put("video_type",Integer.parseInt(pageData.getString("video_type")));
                    pageData.put("video_num",Integer.parseInt(pageData.getString("video_num")));
                    pageData.put("state","0");
                    Long nowtime=System.currentTimeMillis();
                    Date d=new Date(nowtime);
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String  add_time=sdf.format(d);
                    System.out.println(add_time);
                    pageData.put("add_time",add_time);
                    int msg=videoService.addVideo(pageData);
                    if(msg==1){
                        resultMap.put("message","success");
                    }else{
                        resultMap.put("message","fail");
                    }
                }catch(Exception e){
                    resultMap.put("message","fail");
                    e.printStackTrace();
                }
            }
        }
        return new Gson().toJson(resultMap);
    }


    /**
     * 停用启用视频
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/enableORstopVideo", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String enableORstopVideo() throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        PageData pageData=this.getPageData();
        PageData result= videoService.queryVideoById(pageData);
        if(result.getString("state").equals("1"))//如果state=1,则置为0，启用
        {
            pageData.remove("state");
            pageData.put("state","0");
            videoService.updateVideoState(pageData);
            resultMap.put("status", "success");
        }else{                                                      //如果state=0,则置为1，停用
            pageData.remove("state");
            pageData.put("state","1");
            videoService.updateVideoState(pageData);
            resultMap.put("status", "success");
        }
        return new Gson().toJson(resultMap);
    }
}
