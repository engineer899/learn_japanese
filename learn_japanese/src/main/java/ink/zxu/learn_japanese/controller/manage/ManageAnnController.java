package ink.zxu.learn_japanese.controller.manage;

import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.AnnService;
import ink.zxu.learn_japanese.utils.Page;
import ink.zxu.learn_japanese.utils.PageData;
import ink.zxu.learn_japanese.utils.BaseController;
import ink.zxu.learn_japanese.utils.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/manage/annController",produces = "text/html;charset=UTF-8")
public class ManageAnnController extends  BaseController {
    @Resource
    AnnService annService;
    /**
     * 跳转到公告列表视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/ann_list")
    public ModelAndView ann_list(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("announcement/ann_list");
        return mv;
    }

    /**
     *公告信息列表查询
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryAnnInfoJson", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryLawInfoJson(Page page) throws Exception {
        PageData pd = this.getPageData();
        // 设置分页
        page.setPd(pd);
        // 获取所有数据
        List<PageData> list =annService.queryAnnInfoListPage(page);
        PageData userInfo = new PageData();
        // 设置返回数据和视图
        userInfo.put("code", 0);
        userInfo.put("data", list);
        userInfo.put("msg", "");
        return new Gson().toJson(userInfo);
    }

    /**
     * 删除公告
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteAnn", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteAnn() throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        PageData pageData=this.getPageData();
        String a =pageData.getString("id");
        int result= annService.deleteAnn(pageData);
        if(result>0){
            resultMap.put("status", "success");
        }
        return new Gson().toJson(resultMap);
    }

    /**
     * 跳转到公告添加视图
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/Ann_add")
    public ModelAndView addVideo(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        // 设置返回视图和配置项数据
        mv.addObject("pd", pd);
        mv.setViewName("announcement/ann_add");
        return mv;
    }

    /**
     * 添加公告
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addAnn")
    @ResponseBody
    public String addVideo(MultipartHttpServletRequest Request) throws Exception {
        Map<String,String> resultMap=new HashMap<>();
       PageData pageData=this.getPageData();
       try{
                    Long nowtime=System.currentTimeMillis();
                    Date d=new Date(nowtime);
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String  add_time=sdf.format(d);
                    pageData.put("id", UUIDUtil.getUid());
                    pageData.put("add_time",add_time);
                    int msg=annService.addAnn(pageData);
                    if(msg==1){
                        resultMap.put("message","success");
                    }else{
                        resultMap.put("message","fail");
                    }
                }catch(Exception e){
                    resultMap.put("message","fail");
                    e.printStackTrace();
                }
        return new Gson().toJson(resultMap);
    }

    /**
     * 跳转到公告详情
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/ann_details")
    public ModelAndView queryAnnDetails() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        PageData result=annService.queryAnnById(pd);
        // 设置返回视图和配置项数据
        mv.addObject("result", result);
        mv.setViewName("announcement/ann_details");
        return mv;
    }

}
