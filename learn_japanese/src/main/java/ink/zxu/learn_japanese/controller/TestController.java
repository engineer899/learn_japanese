package ink.zxu.learn_japanese.controller;

import com.google.gson.Gson;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 张伟
 * @date 2019/10/20 18:48
 */
@Controller
@RequestMapping(value = "/test")
public class TestController extends BaseController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/upload_view")
    public ModelAndView upload() {
        PageData pd = this.getPageData();
        ModelAndView mv = this.getModelAndView();
        mv.addObject("pd", pd);
        mv.setViewName("pages/test/test");
        return mv;
    }

    @RequestMapping(value="/addTest")
    @ResponseBody
    public String addTest(){
        Map<String,String> resultMap=new HashMap<>();
        PageData pageData=this.getPageData();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  add_time=sdf.format(new Date(System.currentTimeMillis()));
        String   test_id=UUIDUtil.getUid();
        String  signal=null;
        pageData.put("test_id",test_id);
        pageData.put("add_time",add_time);
        for(String key:(Set<String>)pageData.keySet()){
            System.out.println(key+":"+pageData.getString(key));
        }
        try {
            int msg=testService.addTest(pageData);
            if(msg==1 && signal!=null){
                resultMap.put("signal",test_id);
            }else{
                resultMap.put("signal","fail");
            }
        }catch(Exception e){
            resultMap.put("signal","fail");
            e.printStackTrace();
        }
        return new Gson().toJson(resultMap);
    }
}
