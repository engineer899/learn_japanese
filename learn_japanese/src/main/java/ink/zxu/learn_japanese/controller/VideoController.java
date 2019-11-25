package ink.zxu.learn_japanese.controller;

import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.VideoService;
import ink.zxu.learn_japanese.utils.BaseController;
import ink.zxu.learn_japanese.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author 张伟
 * @date 2019/10/16 22:35
 */
@Controller
@RequestMapping(value="/video",produces = "text/html;charset=UTF-8")
public class VideoController extends BaseController {
    @Autowired
    private VideoService videoService;


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



}
