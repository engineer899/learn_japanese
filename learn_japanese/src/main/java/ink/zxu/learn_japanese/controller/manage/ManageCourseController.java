package ink.zxu.learn_japanese.controller.manage;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.CourseService;
import ink.zxu.learn_japanese.service.UploadService;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
@RequestMapping(value = "/manage/courseController",produces = "text/html;charset=UTF-8")
public class ManageCourseController extends BaseController {
    @Resource
    private CourseService courseService;

    @Autowired
    private UploadService uploadService;


    /**
     *课程查询列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryCourseListPage", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryCourseListPage() throws Exception {
        PageData pageData=this.getPageData();
        PageHelper.startPage(Integer.parseInt(pageData.getString("currentPage")),Integer.parseInt(pageData.getString("showCount")));
        // 获取所有数据
        List<PageData> list = courseService.queryCourseListPage(pageData);
        PageData pageCount =courseService.queryCourseCount(pageData);
        PageData pageInfo = new PageData();
        // 设置返回数据和视图
        pageInfo.put("code", 0);
        pageInfo.put("count", pageCount.get("count"));
        pageInfo.put("data", list);
        pageInfo.put("msg", "");
        return new Gson().toJson(pageInfo);
    }

    /**
     * 跳转课程添加视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/course_add")
    public ModelAndView course_add(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("course/course_add");
        return mv;
    }

    /**
     * 跳转课程添加视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/courseWare_add")
    public ModelAndView courseWare_add(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("course/courseWare_add");
        return mv;
    }



    /**
     * 添加书本
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addCourse")
    @ResponseBody
    public String addCourse(MultipartHttpServletRequest Request) throws Exception {
        Map<String,String> resultMap=new HashMap<>();
        MultipartFile file= Request.getFile("image_url");
        System.out.println(file.getOriginalFilename());
        if(file.getOriginalFilename().equals("")){
            resultMap.put("message","图片不能为空");
        }else{
            try {
                System.out.println("上传图片"+file);
                PageData pageData=this.getPageData();
                //控制台输出 便于测试
                for(String key:(Set<String>)pageData.keySet()){
                    System.out.println(key+":"+pageData.getString(key));
                }
                Map<String,Object> resultURl=uploadService.courseImageUpload(file);
                pageData.put("course_id", UUIDUtil.getUid());
                pageData.put("image_url",resultURl.get("url"));
                //控制台输出 便于测试
                for(String key:(Set<String>)pageData.keySet()){
                    System.out.println(key+":"+pageData.getString(key));
                }
                int msg=courseService.addCourse(pageData);
                if(msg>0){
                    resultMap.put("message","success");
                }else{
                    resultMap.put("message","fail");
                    Path path =(Path)resultURl.get("filePath");
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }catch(Exception e){
                resultMap.put("message","fail");
                e.printStackTrace();
            }
        }
        return new Gson().toJson(resultMap);
    }


    /**
     *课程详情
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/course_details")
    public ModelAndView course_details() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        List<PageData> result= courseService.queryCourseListPage(pd);
        // 设置返回视图和配置项数据
        mv.addObject("result", result.get(0));
        System.out.println(result.get(0));
        mv.setViewName("course/course_details");
        return mv;
    }


    /**
     * 删除课程
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteCourse", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteCourse() throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        PageData pageData=this.getPageData();
        int result= courseService.deleteCourse(pageData);
        if(result>0){
            resultMap.put("status", "success");
        }else{
            resultMap.put("status", "fail");
        }
        return new Gson().toJson(resultMap);
    }

    /**
     * 跳转课堂管理主页视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/course_list")
    public ModelAndView course_list(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("course/course_list");
        return mv;
    }


    /**
     * 跳转课件页面视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/courseWare_list")
    public ModelAndView  courseWare_list(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("course/courseWare_list");
        return mv;
    }



    /**
     *课件查询列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryCourseWareInfoListPage", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryCourseWareInfoListPage() throws Exception {
        PageData pageData=this.getPageData();
        PageHelper.startPage(Integer.parseInt(pageData.getString("currentPage")),Integer.parseInt(pageData.getString("showCount")));
        // 获取所有数据
        List<PageData> list = courseService.queryCourseWareInfoListPage(pageData);
        PageData pageCount =courseService.queryCourseWareCount(pageData);
        PageData pageInfo = new PageData();
        // 设置返回数据和视图
        pageInfo.put("code", 0);
        pageInfo.put("count", pageCount.get("count"));
        pageInfo.put("data", list);
        pageInfo.put("msg", "");
        return new Gson().toJson(pageInfo);
    }














    /**
     * 添加课件（ppt)
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addCourseWare")
    @ResponseBody
    public String addCourseWare(MultipartHttpServletRequest Request) throws Exception {
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
                    String courseWare_url=uploadService.courseUpload(file);
                    pageData.put("id", UUIDUtil.getUid());
                    pageData.put("courseWare_url",courseWare_url);
                    pageData.put("courseWare_name",(pageData.getString("courseWare_name")));
                    pageData.put("course_id",(pageData.getString("course_id")));
                    pageData.put("courseWare_num",Integer.parseInt(pageData.getString("courseWare_num")));
                    pageData.put("state","0");
                    Long nowtime=System.currentTimeMillis();
                    Date d=new Date(nowtime);
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String  add_time=sdf.format(d);
                    pageData.put("add_time",add_time);
                    int msg=courseService.addCourseWare(pageData);
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
    @RequestMapping(value = "/enableORstopCourseWare", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String enableORstopCourseWare() throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        PageData pageData=this.getPageData();
        PageData result= courseService.queryCourseWareById(pageData);
        if(result.getString("state").equals("1"))//如果state=1,则置为0，启用
        {
            pageData.remove("state");
            pageData.put("state","0");
            courseService.updateCourseWareState(pageData);
            resultMap.put("status", "success");
        }else{                                                      //如果state=0,则置为1，停用
            pageData.remove("state");
            pageData.put("state","1");
            courseService.updateCourseWareState(pageData);
            resultMap.put("status", "success");
        }
        return new Gson().toJson(resultMap);
    }



}
