package ink.zxu.learn_japanese.controller;

import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.CommunicationService;
import ink.zxu.learn_japanese.service.UploadService;
import ink.zxu.learn_japanese.utils.BaseController;
import ink.zxu.learn_japanese.utils.PageData;
import ink.zxu.learn_japanese.utils.SessionManager;
import ink.zxu.learn_japanese.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 张伟
 * @date 2019/9/22 11:28
 */
@Controller
@RequestMapping(value="/communication",produces = "text/html;charset=UTF-8")
public class CommunicationController extends BaseController {

    @Autowired
    private CommunicationService communicationService;

    @Autowired
    private UploadService uploadService;


    private static String uuid;

    @ResponseBody
    @RequestMapping("/showdongtai")
    public String showDongTai() throws Exception {
        List<PageData> pageDataList=null;
        pageDataList=communicationService.showDongtai();
        return new Gson().toJson(pageDataList);
    }

    @ResponseBody
    @RequestMapping("/showcomment")
    public String showComment() throws Exception {
        PageData pageData=this.getPageData();
        List<PageData> pageDataList=null;
        pageDataList=communicationService.showComment(pageData);
        for(String key:(Set<String>)pageData.keySet()){
            System.out.println(key+":"+pageData.getString(key));
        }
        return new Gson().toJson(pageDataList);

    }

    @ResponseBody
    @RequestMapping("/addcontent")
    public String addContent() throws Exception {
        PageData pageData=this.getPageData();
        Map<String,String> resultMap=new HashMap<>();
        String token=pageData.getString("token");
        HttpSession session=SessionManager.getSession(token);
        String openid=(String)session.getAttribute("openid");
        uuid= UUIDUtil.getUid();
        pageData.put("id",uuid);
        pageData.put("openid",openid);
        communicationService.addContent(pageData);
        for(String key:(Set<String>)pageData.keySet()){
            System.out.println(key+":"+pageData.getString(key));
        }
        resultMap.put("message","Success");
        return  new Gson().toJson(resultMap);
    }

    @ResponseBody
    @RequestMapping("/addimage")
    public String addImage(@RequestParam("file") MultipartFile file){
        Map<String,String> resultMap=new HashMap<>();
        if(file.isEmpty()){
            resultMap.put("message","file is null");
        }else{
            try {
                PageData pageData=this.getPageData();
                for(String key:(Set<String>)pageData.keySet()){
                    System.out.println(key+":"+pageData.getString(key));
                }
                String url=uploadService.imageUpload(file);
                pageData.put("content_id",uuid);
                pageData.put("url",url);
                int msg=communicationService.addImage(pageData);
                if(msg==1){
                    resultMap.put("message","success");
                }else{
                    resultMap.put("message","fail");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return new Gson().toJson(resultMap);
    }


    @ResponseBody
    @RequestMapping("/addcomment")
    public String addComment() throws Exception {
        PageData pageData=this.getPageData();
        Map<String,String> resultMap=new HashMap<>();
        String token=pageData.getString("token");
        HttpSession session=SessionManager.getSession(token);
        String openid=(String)session.getAttribute("openid");
        uuid= UUIDUtil.getUid();
        pageData.put("plid",uuid);
        pageData.put("openid",openid);
        communicationService.addComment(pageData);
        for(String key:(Set<String>)pageData.keySet()){
            System.out.println(key+":"+pageData.getString(key));
        }
        resultMap.put("message","Success");
        return  new Gson().toJson(resultMap);
    }
}
