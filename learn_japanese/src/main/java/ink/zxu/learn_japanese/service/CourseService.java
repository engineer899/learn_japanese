package ink.zxu.learn_japanese.service;

import ink.zxu.learn_japanese.dao.DaoSupport;
import ink.zxu.learn_japanese.utils.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 曾焘
 * @date 2020/02/15 21:26
 */
@Service
public class CourseService {
    @Resource(name="daoSupport")
    private DaoSupport dao;

    /**
     * 课程列表查询
     * @return
     * @throws Exception
     */
    public List<PageData> queryCourseListPage(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("courseMapper.queryCourseListPage", pageData);
    }

    /**
     * 课程类别查询
     * @return
     * @throws Exception
     */
    public List<PageData> queryCourseType(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("courseMapper.queryCourseType", pageData);
    }


    /**
     * 课程总数
     * @return
     * @throws Exception
     */
    public PageData queryCourseCount(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("courseMapper.queryCourseCount", pageData);
    }

    /**
     * 课件总数
     * @return
     * @throws Exception
     */
    public PageData queryCourseWareCount(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("courseMapper.queryCourseWareCount", pageData);
    }



    /**
     * 添加课程(书本)
     * @return
     * @throws Exception
     */
    public Integer addCourse(PageData pageData) throws Exception {
        return  (Integer) dao.save("courseMapper.addCourse",pageData);
    }


    /**
     * 删除课程
     * @return
     * @throws Exception
     */
    public Integer deleteCourse(PageData pageData) throws Exception {
        return  (Integer) dao.update("courseMapper.deleteCourse",pageData);
    }

    /**
     * 课件分页查询
     * @param
     * @return
     * @throws Exception
     */
    public List<PageData> queryCourseWareInfoListPage(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("courseMapper.queryCourseWareInfoListPage",pageData);
    }

    /**
     * 查询视频by id
     * @param pageData
     * @return
     * @throws Exception
     */
    public PageData queryCourseWareById(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("courseMapper.queryCourseWareById", pageData);
    }

    /**
     * 启用停用视频
     * @param pageData
     * @return
     * @throws Exception
     */
    public int updateCourseWareState(PageData pageData) throws Exception {
        return (int) dao.update("courseMapper.updateCourseWareState",pageData);
    }

    public int addCourseWare(PageData pageData) throws Exception {
        return (int)dao.save("courseMapper.addCourseWare",pageData);
    }


    /**
     * 展示该课本下的所有信息
     * @param course_id
     * @return
     * @throws Exception
     */
    public List<Map<String,String>> showByCourse_id(String course_id) throws Exception {
        List<Map<String,String>> resultList;
        resultList=(List<Map<String,String>>)dao.findForList("courseMapper.showByCourse_id",course_id);
        return resultList;
    }

}
