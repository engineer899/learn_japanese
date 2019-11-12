package ink.zxu.learn_japanese.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ink.zxu.learn_japanese.service.LoginService;
import ink.zxu.learn_japanese.utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 张伟
 * @date 2019/9/14 20:44
 */
@Controller
@RequestMapping(produces = "text/html;charset=UTF-8")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;


    @PostMapping(value = "user/login")
    @ResponseBody
    public String login() throws Exception {
        PageData pageData=this.getPageData();
        Map<String,String> resultMap= new HashMap<String,String>();
        String code = pageData.getString("code");
        String appid="wx7c62e3d4366a7b0b";
        String secret="9ac561fbfdad7b0b76523eb619495faf";
        GetOpenid getopenid=new GetOpenid();
        //调用访问微信服务器工具方法，传入三个参数获取带有openid、session_key的json字符串
        String jsonId=getopenid.getopenid(appid,code,secret);
        JsonObject jsonObject=new JsonParser().parse(jsonId).getAsJsonObject();

        //从json字符串获取openid和session_key
        String openid=jsonObject.get("openid").getAsString();
        String session_key=jsonObject.get("session_key").getAsString();

        pageData.put("openid",openid);
        pageData.put("session_key",session_key);
        for(String key:(Set<String>)pageData.keySet()){
            System.out.println(key+":"+pageData.getString(key));

        }
        loginService.setLogin(pageData);
        String rd_session = UUIDUtil.getUid();
        resultMap.put("token",rd_session);
        resultMap.put("message","success");
        if(pageData.getString("token")!=null){
            SessionManager.remove(pageData.getString("token"));
        }
        HttpSession newsession= this.getSession(true);
        newsession.setAttribute("openid",openid);
        newsession.setAttribute("session_key",session_key);
        SessionManager.saveSession(newsession,rd_session);
        System.out.println(SessionManager.sessionPool.size()+" "+newsession.toString());
        return new Gson().toJson(resultMap);
    }
    @ResponseBody
    @PostMapping(value = "user/count")
    public String userCount() throws Exception {
        System.out.println("cssfs");
        Integer count=loginService.userCount();
        return count.toString();
    }

    @PostMapping(value = "user/bind")
    public String bind(){
        return "";
    }
}