package ink.zxu.learn_japanese.controller;

import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.CourseService;
import ink.zxu.learn_japanese.service.UploadService;
import ink.zxu.learn_japanese.utils.BaseController;
import ink.zxu.learn_japanese.utils.PageData;
import ink.zxu.learn_japanese.utils.UUIDUtil;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 张伟
 * @date 2019/10/11 21:23
 */
@Controller
@RequestMapping(value = "/course",produces = "text/html;charset=UTF-8")
public class CourseController extends BaseController {
    @Autowired
    private UploadService uploadService;

    @Autowired
    private CourseService courseService;



    @RequestMapping(value = "/upload_view")
    public ModelAndView upload() {
        PageData pd = this.getPageData();
        ModelAndView mv = this.getModelAndView();
        mv.addObject("pd", pd);
        mv.setViewName("pages/course/upload");
        return mv;
    }

    @RequestMapping(value="/add")
    @ResponseBody
    public String addCourse(MultipartHttpServletRequest request) throws Exception {
        Map<String,String> resultMap=new HashMap<>();
        List<MultipartFile> files = request.getFiles("file");
        MultipartFile file = null;
        for (int i = 0; i < files.size(); ++i) {
            System.out.println("进入2"+files.size());
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
                String course_url=uploadService.courseUpload(file);
                pageData.put("id", UUIDUtil.getUid());
                pageData.put("course_url",course_url);
                Long nowtime=System.currentTimeMillis();
                Date d=new Date(nowtime);
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String  add_time=sdf.format(d);
                System.out.println(add_time);
                pageData.put("add_time",add_time);
                int msg=courseService.addCourse(pageData);
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

    @RequestMapping(value = "/show")
    @ResponseBody
    public String showCourse() throws Exception {
        Map<String,List<Map<String,Object>>> resultMap;
        resultMap=courseService.showCourse();
        return new Gson().toJson(resultMap);
    }

}
