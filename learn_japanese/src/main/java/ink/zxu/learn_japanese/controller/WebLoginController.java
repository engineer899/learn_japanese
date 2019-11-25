package ink.zxu.learn_japanese.controller;

import ink.zxu.learn_japanese.entity.SysUser;
import ink.zxu.learn_japanese.service.WebLoginService;
import ink.zxu.learn_japanese.utils.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;





/**
 * @author 张伟
 * @date 2019/11/13 16:21
 */
@Controller
@RequestMapping(value = "/manage",produces = "text/html;charset=utf-8")
public class WebLoginController extends BaseController {
    @Autowired
    private WebLoginService webLoginService;

    private  final Logger logger= LoggerFactory.getLogger(this.getClass());



    @RequestMapping("/login")
    public String toLogin(){
        return "index";
    }

    @RequestMapping("")
    public String index(){

        logger.info("初始化登录页面....");
        return"/base/login";

    }
//    @RequestMapping("/home")
//    public String home(HttpServletResponse response){
//        SysUser ui = this.getCurrentUser();
//        if( ui == null ){
//            try {
//                response.sendRedirect("../login");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        logger.info("初始化home页面....");
//        return"/base/home";
//
//    }

    @RequestMapping("/loginUser")
    public String loginUser(HttpServletRequest request,String username,String password,String vcode) {

        HttpSession session = request.getSession();
        //转化成小写字母
        vcode = vcode.toLowerCase();
        String v = (String)session.getAttribute("_code");//还可以读取一次后把验证码清空，这样每次登录都必须获取验证码;
        logger.info("获取保存vcode:"+v);
        logger.info("验证vcode:"+vcode);
        if(!vcode.equals(v)){
            logger.info("对用户[" + username + "]验证码不通过");
            request.setAttribute("message", "验证码不正确");
            return "/base/login";//返回登录页面
        }

        logger.info("进行账号"+username+",密码验证"+password+".....");
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);   //完成登录
            SysUser user=(SysUser) subject.getPrincipal();
            session.setAttribute("user", user);
            session.setAttribute("clickId","home");
            return "/base/loadHome";
        }catch(UnknownAccountException uae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            request.setAttribute("message", "未知账户");
            return "/base/login";//返回登录页面
        }catch(IncorrectCredentialsException ice){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            request.setAttribute("message", "密码不正确");
            return "/base/login";//返回登录页面
        }catch(LockedAccountException lae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            request.setAttribute("message", "账户已锁定");
            return "/base/login";//返回登录页面
        }catch(ExcessiveAttemptsException eae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            request.setAttribute("message", "用户名或密码错误次数过多");
            return "/base/login";//返回登录页面
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            request.setAttribute("message", "用户名或密码不正确");
            return "/base/login";//返回登录页面
        }

    }

    @RequestMapping("/logOut")
    public String logOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        session.removeAttribute("user");
        return "/base/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }









}
