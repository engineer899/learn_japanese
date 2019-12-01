package ink.zxu.learn_japanese.controller.manage;

import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.UploadService;
import ink.zxu.learn_japanese.service.VideoService;
import ink.zxu.learn_japanese.service.WordService;
import ink.zxu.learn_japanese.utils.BaseController;
import ink.zxu.learn_japanese.utils.Page;
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
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangwei
 * @date 2019/11/30 19:39
 */
@Controller
@RequestMapping(value = "/manage/wordController")
public class ManageWordController  extends BaseController {



    @Resource
    private WordService wordService;

    @Autowired
    private UploadService uploadService;


    /**
     * 跳转单词列表视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/word_list")
    public ModelAndView word_list(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("word/word_list");
        return mv;
    }


    /**
     * 跳转到单词添加视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/word_add")
    public ModelAndView word_add(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("word/word_add");
        return mv;
    }


    /**
     *视频信息列表查询
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryWordInfoJson", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryWordInfoJson(Page page) throws Exception {
        PageData pd = this.getPageData();
        // 设置分页
        page.setPd(pd);
        // 获取所有数据
        List<PageData> list = wordService.queryWordInfoListPage(page);
        PageData userInfo = new PageData();
        // 设置返回数据和视图
        userInfo.put("code", 0);
        userInfo.put("count", page.getTotalResult());
        userInfo.put("data", list);
        userInfo.put("msg", "");
        return new Gson().toJson(userInfo);
    }




    /**
     * 添加单词
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addWord")
    @ResponseBody
    public String addWord(MultipartHttpServletRequest Request) throws Exception {
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
                    Map<String,Object> resultURl=uploadService.voiceUpload(file);
                    pageData.put("word_id", UUIDUtil.getUid());
                    pageData.put("voice_id", UUIDUtil.getUid());
                    pageData.put("analy_id", UUIDUtil.getUid());
                    pageData.put("eg_id", UUIDUtil.getUid());
                    pageData.put("voice_url",resultURl.get("url"));
                    pageData.put("sentence",pageData.get("sentence_01"));
                    pageData.put("translate",pageData.get("translate_01"));
                    pageData.put("type","1");
                    pageData.put("state","0");
                    Long nowtime=System.currentTimeMillis();
                    Date d=new Date(nowtime);
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String  add_time=sdf.format(d);
                    System.out.println(add_time);
                    pageData.put("add_time",add_time);
                    //控制台输出 便于测试
                    for(String key:(Set<String>)pageData.keySet()){
                        System.out.println(key+":"+pageData.getString(key));
                    }
                    int msg=wordService.addWord(pageData);
                    if(msg==0){
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
        }
        return new Gson().toJson(resultMap);
    }
}
