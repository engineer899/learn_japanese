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

/**
 * @author zhangwei
 * @date 2019/12/5 0:17
 */
@Controller
@RequestMapping(value = "/wordController")
public class WordController extends BaseController {
    @Resource
    private WordService wordService;


    @ResponseBody
    @RequestMapping("/queryWordTitle")
    public String queryWordTitle() throws Exception {
        PageData pd=this.getPageData();
        List<PageData> pageDataList=null;
        pageDataList=wordService.queryWordTitle(pd);
        return new Gson().toJson(pageDataList);
    }

    @ResponseBody
    @RequestMapping("/clickWordTest")
    public String clickWordTest() throws Exception {
        PageData pd=this.getPageData();
        String   token=pd.getString("token");
        HttpSession session= SessionManager.getSession(token);
        String user_id=(String)session.getAttribute("openid");
        pd.put("user_id",user_id);
        List<PageData> pageDataList=null;
        pageDataList=wordService.clickWordTest(pd);
        return new Gson().toJson(pageDataList);
    }
}
