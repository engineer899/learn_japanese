package ink.zxu.learn_japanese.controller;

import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.AnnService;
import ink.zxu.learn_japanese.service.TestService;
import ink.zxu.learn_japanese.utils.BaseController;
import ink.zxu.learn_japanese.utils.PageData;
import ink.zxu.learn_japanese.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 曾焘
 * @date 2020/02/05 17：42
 */
@Controller
@RequestMapping(value = "/ann")
public class AnnController extends BaseController {

    @Autowired
    private AnnService annService;

//    查所有公告
    @ResponseBody
    @RequestMapping("/showAllAnnouncement")
    public String showAllAnnouncement() throws Exception {
        PageData pageData=this.getPageData();
        List<PageData> pageDataList=null;
        pageDataList=annService.showAllAnnouncement(pageData);
        for(String key:(Set<String>)pageData.keySet()){
            System.out.println(key+":"+pageData.getString(key));
        }
        return new Gson().toJson(pageDataList);
    }


}
