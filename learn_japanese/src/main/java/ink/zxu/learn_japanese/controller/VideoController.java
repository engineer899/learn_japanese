package ink.zxu.learn_japanese.controller;

import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.UploadService;
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

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;
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

    @Autowired
    private UploadService uploadService;

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

    @RequestMapping(value="/add")
    @ResponseBody
    public String addVideo(HttpServletRequest request) throws Exception {
        Map<String,String> resultMap=new HashMap<>();
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = params.getFiles("file");
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

}
