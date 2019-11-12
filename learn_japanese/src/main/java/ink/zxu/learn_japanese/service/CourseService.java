package ink.zxu.learn_japanese.service;

import ink.zxu.learn_japanese.dao.DaoSupport;
import ink.zxu.learn_japanese.utils.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张伟
 * @date 2019/10/11 21:26
 */
@Service
public class CourseService {
    @Resource(name="daoSupport")
    private DaoSupport dao;

    public int addCourse(PageData pageData) throws Exception {
        return (int)dao.save("courseMapper.addCourse",pageData);
    }

    public Map<String,List<Map<String,Object>>> showCourse() throws Exception {
        Map<String,List<Map<String,Object>>> resultMap=new HashMap<>();
        List<Map<String,Object>> resultList;
        List<Integer> typeList=( List<Integer>)dao.findForList("courseMapper.showTypes");
        for(Integer i:typeList){
            resultList=(List<Map<String,Object>>)dao.findForList("courseMapper.showByType",i);
            resultMap.put("type_"+i.toString(),resultList);
        }
        return resultMap;
    }

}
