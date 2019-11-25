package ink.zxu.learn_japanese.service;

import ink.zxu.learn_japanese.dao.DaoSupport;
import ink.zxu.learn_japanese.utils.Page;
import ink.zxu.learn_japanese.utils.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张伟
 * @date 2019/10/16 22:36
 */
@Service
public class VideoService {
    @Resource(name="daoSupport")
    private DaoSupport dao;

    public int addVideo(PageData pageData) throws Exception {
        return (int)dao.save("videoMapper.addVideo",pageData);
    }

    public List<Map<String,String>> countByType(){
        List<Map<String,String>> resultList;
        resultList=(List<Map<String,String>>)dao.findForList("videoMapper.CountByType");
        return resultList;
    }

    public List<Map<String,String>> showByType(Integer video_type) throws Exception {
        List<Map<String,String>> resultList;
        resultList=(List<Map<String,String>>)dao.findForList("videoMapper.showByType",video_type);
        return resultList;
    }


    public Map<String, List<Map<String,Object>>> showVideo() throws Exception {
        Map<String,List<Map<String,Object>>> resultMap=new HashMap<>();
        List<Map<String,Object>> resultList;
        List<Integer> typeList=( List<Integer>)dao.findForList("videoMapper.showTypes");
        for(Integer i:typeList){
            resultList=(List<Map<String,Object>>)dao.findForList("videoMapper.showByType",i);
            resultMap.put("type_"+i.toString(),resultList);
        }
        return resultMap;
    }


    /**
     * 微课视频分页查询
     * @param page
     * @return
     * @throws Exception
     */
    public List<PageData> queryVideoInfoListPage(Page page) throws Exception {
        return (List<PageData>) dao.findForList("videoMapper.queryVideoInfoListPage", page);
    }

    /**
     * 查询视频by id
     * @param page
     * @return
     * @throws Exception
     */
    public PageData queryVideoById(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("videoMapper.queryVideoById", pageData);
    }



}
