package ink.zxu.learn_japanese.controller.manage;

import ink.zxu.learn_japanese.utils.PageData;
import ink.zxu.learn_japanese.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/manage/manageAnnController",produces = "text/html;charset=UTF-8")
public class ManageAnnController extends  BaseController {

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
}
