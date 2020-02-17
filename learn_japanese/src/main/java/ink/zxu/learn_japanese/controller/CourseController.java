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
 * @author 曾焘
 * @date 2020/02/16 15:23
 */
@Controller
@RequestMapping(value = "/course",produces = "text/html;charset=UTF-8")
public class CourseController extends BaseController {
    @Autowired
    private UploadService uploadService;

    @Autowired
    private CourseService courseService;

    /**
     * 通过书id展示书内的所有视频的所有信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/showByCourse_id")
    @ResponseBody
    public String showByName() throws Exception {
        PageData pageData=this.getPageData();
        List<Map<String,String>> resultList;
        resultList=courseService.showByCourse_id((pageData.getString("course_id")));
        return new Gson().toJson(resultList);
    }

    /**
     * 展示视频系列和视频书名
     * @return
     */
    @RequestMapping(value="/showAllSeries")
    @ResponseBody
    public String showAllSeries() throws Exception {
        PageData pageData=this.getPageData();
        List<Map<String,Object>> result=new ArrayList<>();
        List<PageData> TypeList=courseService.queryCourseType(pageData);
        for(PageData temp:TypeList){
            pageData.put("type",temp.getString("type"));
            List<PageData> list = new ArrayList<>();
            Map<String,Object> tempMap=new HashMap<>();
            tempMap.put("type",temp.getString("type"));
            list=courseService.queryCourseListPage(pageData);
            tempMap.put("course",list);
            result.add(tempMap);
        }
        return new Gson().toJson(result);
    }



}
