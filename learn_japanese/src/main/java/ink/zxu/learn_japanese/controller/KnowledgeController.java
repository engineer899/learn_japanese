package ink.zxu.learn_japanese.controller;

import com.google.gson.Gson;
import ink.zxu.learn_japanese.service.KnowService;
import ink.zxu.learn_japanese.utils.BaseController;
import ink.zxu.learn_japanese.utils.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



/**
 * @author zhangwei
 * @date 2020/2/9 14:52
 */
@Controller
@RequestMapping(value = "/knowController",produces = "text/html;charset=UTF-8")
public class KnowledgeController extends BaseController {
    @Resource
    private KnowService knowService;

    //查询类型
    @ResponseBody
    @RequestMapping("/queryType")
    public String queryType() throws Exception {
        PageData pd=this.getPageData();
        List<PageData> list = knowService.queryTypeList(pd);
        for(int i=0;i<list.size();i++){
            pd.put("type_id",list.get(i).getString("type_id"));
            List<PageData> know_list=new ArrayList<>();
            know_list = knowService.queryKnowByID(pd);
            list.get(i).put("type_count",know_list.size());
            list.get(i).put("know_list",know_list);
        }
        System.out.println(list);
        return new Gson().toJson(list);
    }


    //查询类型
    @ResponseBody
    @RequestMapping("/queryKnow")
    public String queryKnow() throws Exception {
        PageData pd=this.getPageData();
        List<PageData> list = knowService.queryKnowList(pd);
        System.out.println(list.get(0));
        return new Gson().toJson(list.get(0));
    }

}
