package ink.zxu.learn_japanese.controller;

import com.github.pagehelper.PageHelper;
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
import java.util.ArrayList;
import java.util.HashMap;
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


    //查询课程列表
    @ResponseBody
    @RequestMapping("/queryCourseList")
    public String queryCourseList() throws Exception {
        PageData pageData=this.getPageData();
        List<Map<String,Object>> result=new ArrayList<>();
        List<PageData> TypeList=wordService.queryCourseType(pageData);
        for(PageData temp:TypeList){
            pageData.put("type",temp.getString("type"));
            List<PageData> list = new ArrayList<>();
            Map<String,Object> tempMap=new HashMap<>();
            tempMap.put("type",temp.getString("type"));
            list=wordService.queryCourseListPage(pageData);
            tempMap.put("course",list);
            result.add(tempMap);
        }
        return new Gson().toJson(result);
    }



    //查询单词库
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

    //查询单词表
    @ResponseBody
    @RequestMapping("/queryWordList")
    public String queryWordList() throws Exception {
        PageData pageData=this.getPageData();
        PageHelper.startPage(Integer.parseInt(pageData.getString("currentPage")),Integer.parseInt(pageData.getString("showCount")));
        // 获取所有数据
        List<PageData> list = wordService.queryWordListPage(pageData);
        PageData pageCount =wordService.queryWordCount(pageData);
        PageData pageInfo = new PageData();
        // 设置返回数据和视图
        Long along=(Long) pageCount.get("count");
        Integer count=along.intValue();
        Integer countPage=0;
        Integer showCount=Integer.parseInt(pageData.getString("showCount"));
        if(count!=0){
            if(count%showCount==0)
                countPage = count/showCount;
            else
                countPage = count/showCount+1;
        }
        pageInfo.put("count", count);
        pageInfo.put("countPage", countPage);
        pageInfo.put("data", list);
        return new Gson().toJson(pageInfo);
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
    @RequestMapping("/addWordRecord")
    public String addWordRecord() throws Exception {
        PageData pd=this.getPageData();
        String   token=pd.getString("token");
        HttpSession session= SessionManager.getSession(token);
        String user_id=(String)session.getAttribute("openid");
        pd.put("user_id",user_id);
        PageData resultMap=null;
        resultMap=wordService.addWordRecord(pd);
        System.out.println(resultMap);
        return new Gson().toJson(resultMap);
    }

//    //增加单词答题记录
//    @ResponseBody
//    @RequestMapping("/addAnswerRecord")
//    public String addAnswerRecord() throws Exception {
//        PageData pd=this.getPageData();
//        String   token=pd.getString("token");
//        HttpSession session= SessionManager.getSession(token);
//        String user_id=(String)session.getAttribute("openid");
//        pd.put("user_id",user_id);
//        PageData resultMap=null;
//        resultMap=wordService.addAnswerRecord(pd);
//        System.out.println(resultMap);
//        return new Gson().toJson(resultMap);
//    }
}
