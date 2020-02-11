package ink.zxu.learn_japanese.controller.manage;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.KnowService;
import ink.zxu.learn_japanese.service.UploadService;
import ink.zxu.learn_japanese.utils.BaseController;
import ink.zxu.learn_japanese.utils.PageData;
import ink.zxu.learn_japanese.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangwei
 * @date 2020/2/9 17:53
 */
@Controller
@RequestMapping(value = "/manage/knowController",produces = "text/html;charset=UTF-8")
public class ManageKnowController extends BaseController {

    @Resource
    private KnowService knowService;

    @Autowired
    private UploadService uploadService;

    /**
     * 跳转章节列表视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/know_list")
    public ModelAndView know_list(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("know/know_list");
        return mv;
    }

    /**
     * 跳转章节添加视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/know_add")
    public ModelAndView know_add(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("know/know_add");
        return mv;
    }

    /**
     * 跳转章节列表视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/type_list")
    public ModelAndView type_list(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("know/type_list");
        return mv;
    }

    /**
     * 跳转章节添加视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/type_add")
    public ModelAndView type_add(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("know/type_add");
        return mv;
    }
    /**
     * 上传图片
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/uploadImage")
    @ResponseBody
    public String uploadImage(MultipartFile file) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> data = new HashMap<>();
        String resultURl=null;
        System.out.println(file.getOriginalFilename());
        try {
            resultURl=uploadService.imageUpload(file);
            data.put("src",resultURl);
            data.put("title",file.getOriginalFilename());
            resultMap.put("data",data);
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
        }catch(Exception e){
            resultMap.put("data","");
            resultMap.put("code", -1);
            resultMap.put("msg", "上传失败");
            e.printStackTrace();
        }
        return new Gson().toJson(resultMap);
    }


    /**
     *类型查询列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryTypeList", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryTypeList() throws Exception {
        PageData pageData=this.getPageData();
        PageHelper.startPage(Integer.parseInt(pageData.getString("currentPage")),Integer.parseInt(pageData.getString("showCount")));
        // 获取所有数据
        List<PageData> list = knowService.queryTypeList(pageData);
        PageData pageCount =knowService.queryTypeCount(pageData);
        PageData pageInfo = new PageData();
        // 设置返回数据和视图
        pageInfo.put("code", 0);
        pageInfo.put("count", pageCount.get("count"));
        pageInfo.put("data", list);
        pageInfo.put("msg", "");
        return new Gson().toJson(pageInfo);
    }

    /**
     * 添加类型
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addType")
    @ResponseBody
    public String addType() throws Exception {
        Map<String,String> resultMap=new HashMap<>();
        PageData pageData=this.getPageData();
        if(pageData.getString("type_name").equals("")){
            resultMap.put("message","类型不能为空");
        }else{
            List<PageData> chapterList= knowService.queryTypeByName(pageData);
            if (chapterList.size()>0){
                resultMap.put("message","该章节已经存在！");
            }else{
                try {
                    pageData.put("type_id", UUIDUtil.getUid());
                    //控制台输出 便于测试
                    for(String key:(Set<String>)pageData.keySet()){
                        System.out.println(key+":"+pageData.getString(key));
                    }
                    int msg=knowService.addType(pageData);
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
     *小知识查询列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryKnowList", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryKnowList() throws Exception {
        PageData pageData=this.getPageData();
        PageHelper.startPage(Integer.parseInt(pageData.getString("currentPage")),Integer.parseInt(pageData.getString("showCount")));
        // 获取所有数据
        List<PageData> list = knowService.queryKnowList(pageData);
        PageData pageCount =knowService.queryKnowCount(pageData);
        PageData pageInfo = new PageData();
        // 设置返回数据和视图
        pageInfo.put("code", 0);
        pageInfo.put("count", pageCount.get("count"));
        pageInfo.put("data", list);
        pageInfo.put("msg", "");
        return new Gson().toJson(pageInfo);
    }

    /**
     * 添加小知识
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addKnow")
    @ResponseBody
    public String addKnow() throws Exception {
        Map<String,String> resultMap=new HashMap<>();
        PageData pageData=this.getPageData();
        if(pageData.getString("title").equals("")){
            resultMap.put("message","标题不能为空");
        }else{

            try {

                pageData.put("know_id", UUIDUtil.getUid());
                int msg=knowService.addKnow(pageData);
                if(msg>0){
                    resultMap.put("message","success");
                }else{
                }
                //控制台输出 便于测试
                for(String key:(Set<String>)pageData.keySet()){
                    System.out.println(key+":"+pageData.getString(key));
                }
            }catch(Exception e){
                resultMap.put("message","fail");
                e.printStackTrace();
            }

        }
        return new Gson().toJson(resultMap);
    }





}
