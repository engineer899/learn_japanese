package ink.zxu.learn_japanese.controller;

import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.WordService;
import ink.zxu.learn_japanese.utils.BaseController;
import ink.zxu.learn_japanese.utils.PageData;
import ink.zxu.learn_japanese.utils.SessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author zhangwei
 * @date 2019/12/5 0:17
 */
@Controller
@RequestMapping(value = "/wordController")
public class WordController extends BaseController {
    @Resource
    private WordService wordService;


    //查询单词库的单词
    @ResponseBody
    @RequestMapping("/queryWordTitle")
    public String queryWordTitle() throws Exception {
        PageData pd=this.getPageData();
        String   token=pd.getString("token");
        HttpSession session= SessionManager.getSession(token);
        String user_id=(String)session.getAttribute("openid");//获取用户openid
        pd.put("user_id",user_id);
        List<PageData> pageDataList=null;
        pageDataList=wordService.queryWordTitle(pd);
        return new Gson().toJson(pageDataList);
    }

    //点击开始答题记录
    @ResponseBody
    @RequestMapping("/clickWordTest")
    public String clickWordTest() throws Exception {
        PageData pd=this.getPageData();
        String   token=pd.getString("token");
        HttpSession session= SessionManager.getSession(token);
        String user_id=(String)session.getAttribute("openid");//获取用户openid
        pd.put("user_id",user_id);
        Map<String,Object> resultMap=null;
        resultMap=wordService.clickWordTest(pd);
        System.out.println(resultMap);
        return new Gson().toJson(resultMap);
    }

    //增加单词答题记录
    @ResponseBody
    @RequestMapping("/addAnswerRecord")
    public String addAnswerRecord() throws Exception {
        PageData pd=this.getPageData();
        String   token=pd.getString("token");
        HttpSession session= SessionManager.getSession(token);
        String user_id=(String)session.getAttribute("openid");
        pd.put("user_id",user_id);
        PageData resultMap=null;
        resultMap=wordService.addAnswerRecord(pd);
        System.out.println(resultMap);
        return new Gson().toJson(resultMap);
    }
}
