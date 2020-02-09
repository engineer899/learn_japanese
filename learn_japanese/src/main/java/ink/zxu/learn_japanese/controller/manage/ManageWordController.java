package ink.zxu.learn_japanese.controller.manage;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.ExcelImportService;
import ink.zxu.learn_japanese.service.UploadService;

import ink.zxu.learn_japanese.service.WordService;
import ink.zxu.learn_japanese.utils.BaseController;
import ink.zxu.learn_japanese.utils.PageData;
import ink.zxu.learn_japanese.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    @Autowired
    private ExcelImportService excelImportService;

    /**
     * 跳转章节列表视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/chapter_list")
    public ModelAndView chapter_list(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("word/chapter_list");
        return mv;
    }

    /**
     * 跳转章节添加视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/chapter_add")
    public ModelAndView chapter_add(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("word/chapter_add");
        return mv;
    }

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
     * 跳转课程展示视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/course_list")
    public ModelAndView course_list(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("word/course_list");
        return mv;
    }

    /**
     * 跳转课程添加视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/course_add")
    public ModelAndView course_add(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("word/course_add");
        return mv;
    }

    /**
     *课程详情
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/course_details")
    public ModelAndView course_details() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        List<PageData> result= wordService.queryCourseListPage(pd);
        // 设置返回视图和配置项数据
        mv.addObject("result", result.get(0));
        mv.setViewName("word/course_details");
        return mv;
    }

    /**
     *课程查询列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryCourseListPage", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryCourseListPage() throws Exception {
        PageData pageData=this.getPageData();
        PageHelper.startPage(Integer.parseInt(pageData.getString("currentPage")),Integer.parseInt(pageData.getString("showCount")));
        // 获取所有数据
        List<PageData> list = wordService.queryCourseListPage(pageData);
        PageData pageCount =wordService.queryCourseCount(pageData);
        PageData pageInfo = new PageData();
        // 设置返回数据和视图
        pageInfo.put("code", 0);
        pageInfo.put("count", pageCount.get("count"));
        pageInfo.put("data", list);
        pageInfo.put("msg", "");
        return new Gson().toJson(pageInfo);
    }


    /**
     *课程查询列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryChapterListPage", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryChapterListPage() throws Exception {
        PageData pageData=this.getPageData();
        PageHelper.startPage(Integer.parseInt(pageData.getString("currentPage")),Integer.parseInt(pageData.getString("showCount")));
        // 获取所有数据
        List<PageData> list = wordService.queryChapterListPage(pageData);
        PageData pageCount =wordService.queryChapterCount(pageData);
        PageData pageInfo = new PageData();
        // 设置返回数据和视图
        pageInfo.put("code", 0);
        pageInfo.put("count", pageCount.get("count"));
        pageInfo.put("data", list);
        pageInfo.put("msg", "");
        return new Gson().toJson(pageInfo);
    }

    /**
     * 添加课程
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addChapter")
    @ResponseBody
    public String addChapter() throws Exception {
        Map<String,String> resultMap=new HashMap<>();
        PageData pageData=this.getPageData();
        if(pageData.getString("chapter_name").equals("")){
            resultMap.put("message","章节不能为空");
        }else{
            List<PageData> chapterList= wordService.queryChapterByName(pageData);
            if (chapterList.size()>0){
                resultMap.put("message","该章节已经存在！");
            }else{
                try {
                    //控制台输出 便于测试
                    for(String key:(Set<String>)pageData.keySet()){
                        System.out.println(key+":"+pageData.getString(key));
                    }
                    pageData.put("chapter_id", UUIDUtil.getUid());
                    int msg=wordService.addChapter(pageData);
                    if(msg>0){
                        resultMap.put("message","success");
                    }else{
                    }
                }catch(Exception e){
                    resultMap.put("message","fail");
                    e.printStackTrace();
                }
            }
        }
        return new Gson().toJson(resultMap);
    }


    /**
     * 添加课程
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addCourse")
    @ResponseBody
    public String addCourse(MultipartHttpServletRequest Request) throws Exception {
        Map<String,String> resultMap=new HashMap<>();
        MultipartFile file= Request.getFile("image_url");
        System.out.println(file.getOriginalFilename());
        if(file.getOriginalFilename().equals("")){
            resultMap.put("message","图片不能为空");
        }else{
            try {
                System.out.println("上传图片"+file);
                PageData pageData=this.getPageData();
                //控制台输出 便于测试
                for(String key:(Set<String>)pageData.keySet()){
                    System.out.println(key+":"+pageData.getString(key));
                }
                Map<String,Object> resultURl=uploadService.courseImageUpload(file);
                pageData.put("course_id", UUIDUtil.getUid());
                pageData.put("image_url",resultURl.get("url"));
                //控制台输出 便于测试
                for(String key:(Set<String>)pageData.keySet()){
                    System.out.println(key+":"+pageData.getString(key));
                }
                int msg=wordService.addCourse(pageData);
                if(msg>0){
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
        return new Gson().toJson(resultMap);
    }


    /**
     * 删除课程
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteCourse", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteCourse() throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        PageData pageData=this.getPageData();
        int result= wordService.deleteCourse(pageData);
        if(result>0){
            resultMap.put("status", "success");
        }else{
            resultMap.put("status", "fail");
        }
        return new Gson().toJson(resultMap);
    }




    /**
     * 删除单词
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteWordById", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteWordById() throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        PageData pageData=this.getPageData();
        int result= wordService.deleteWordById(pageData);
        if(result==0){
            resultMap.put("status", "success");
        }else{
            resultMap.put("status", "fail");
        }
        return new Gson().toJson(resultMap);
    }


    /**
     *单词查询列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryWordInfoJson", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryWordInfoJson() throws Exception {
        PageData pageData=this.getPageData();
        PageHelper.startPage(Integer.parseInt(pageData.getString("currentPage")),Integer.parseInt(pageData.getString("showCount")));
        // 获取所有数据
        List<PageData> list = wordService.queryWordListPage(pageData);
        PageData pageCount =wordService.queryWordCount(pageData);
        PageData pageInfo = new PageData();
        // 设置返回数据和视图
        pageInfo.put("code", 0);
        pageInfo.put("count", pageCount.get("count"));
        pageInfo.put("data", list);
        pageInfo.put("msg", "");
        return new Gson().toJson(pageInfo);
    }


    //excel导入单词
    @ResponseBody
    @PostMapping("/excelImport")
    public String addStore_Test(@RequestParam("file") MultipartFile file, @RequestParam("chapter_id") String chapter_id ) {
        try {
            String msg= excelImportService.ExcelImport(file,chapter_id);
            System.out.println(msg);
            if(msg.contains("[")){
                return "error msg";
            }else{
                return msg;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Import exception";
        }
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
        PageData pageData=this.getPageData();
        Map<String,Object> resultURl=null;
        MultipartFile file= Request.getFile("voice_url");
        System.out.println(file.getOriginalFilename());
        try {
            //控制台输出 便于测试
            for(String key:(Set<String>)pageData.keySet()){
                System.out.println(key+":"+pageData.getString(key));
            }
            if(file.getOriginalFilename().equals("")){
                pageData.put("voice_url","null");
            }else{
                resultURl=uploadService.voiceUpload(file);
                pageData.put("voice_url",resultURl.get("url"));
            }
            pageData.put("word_id", UUIDUtil.getUid());
            int msg=wordService.addWord(pageData);
            if(msg>0){
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
        return new Gson().toJson(resultMap);
    }




//    /**
//     * 添加单词
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value="/addWord")
//    @ResponseBody
//    public String addWord(MultipartHttpServletRequest Request) throws Exception {
//        Map<String,String> resultMap=new HashMap<>();
//        List<MultipartFile> files = Request.getFiles("file");
//        MultipartFile file = null;
//        for (int i = 0; i < files.size(); ++i) {
//            System.out.println("进入"+files.size());
//            file=files.get(i);
//            if(file.isEmpty()){
//                resultMap.put("message","file is null");
//            }else{
//                try {
//                    PageData pageData=this.getPageData();
//                    //控制台输出 便于测试
//                    for(String key:(Set<String>)pageData.keySet()){
//                        System.out.println(key+":"+pageData.getString(key));
//                    }
//                    Map<String,Object> resultURl=uploadService.voiceUpload(file);
//                    pageData.put("word_id", UUIDUtil.getUid());
//                    pageData.put("voice_id", UUIDUtil.getUid());
//                    pageData.put("analy_id", UUIDUtil.getUid());
//                    pageData.put("eg_id", UUIDUtil.getUid());
//                    pageData.put("voice_url",resultURl.get("url"));
//                    pageData.put("sentence",pageData.get("sentence_01"));
//                    pageData.put("translate",pageData.get("translate_01"));
//                    pageData.put("type","1");
//                    pageData.put("state","0");
//                    Long nowtime=System.currentTimeMillis();
//                    Date d=new Date(nowtime);
//                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    String  add_time=sdf.format(d);
//                    System.out.println(add_time);
//                    pageData.put("add_time",add_time);
//                    //控制台输出 便于测试
//                    for(String key:(Set<String>)pageData.keySet()){
//                        System.out.println(key+":"+pageData.getString(key));
//                    }
//                    int msg=wordService.addWord(pageData);
//                    if(msg==0){
//                        resultMap.put("message","success");
//                    }else{
//                        resultMap.put("message","fail");
//                        Path path =(Path)resultURl.get("filePath");
//                        try {
//                            Files.deleteIfExists(path);
//                        } catch (IOException e1) {
//                            e1.printStackTrace();
//                        }
//                    }
//                }catch(Exception e){
//                    resultMap.put("message","fail");
//                    e.printStackTrace();
//                }
//            }
//        }
//        return new Gson().toJson(resultMap);
//    }
}
