package ink.zxu.learn_japanese.service;

import com.sun.javafx.collections.MappingChange;
import ink.zxu.learn_japanese.dao.DaoSupport;
import ink.zxu.learn_japanese.utils.GetOpenid;
import ink.zxu.learn_japanese.utils.Page;
import ink.zxu.learn_japanese.utils.PageData;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
     * @param pageData
     * @return
     * @throws Exception
     */
    public PageData queryVideoById(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("videoMapper.queryVideoById", pageData);
    }

    /**
     * 启用停用视频
     * @param pageData
     * @return
     * @throws Exception
     */
    public int updateVideoState(PageData pageData) throws Exception {
        return (int) dao.update("videoMapper.updateVideoState",pageData);
    }
    /**
     * 查看视频下方所有要素
     * @param pageData
     * @return
     * @throws Exception
     */
    public List<PageData> showAllContentById(PageData pageData) throws Exception {
        //①找评论②找知识点③标记当前用户点过赞的评论
        List<PageData> pageData1=(List<PageData>) dao.findForList("videoMapper.showAllContentById", pageData);
        pageData1.add(  (PageData) dao.findForObject("videoMapper.showKnowledgeById", pageData)  );
        return pageData1;
    }

    /**
     * 当前评论是否被当前用户赞过
     * zan1  （+ 1评论赞）      zan3 （  -1 评论赞）
     * zan4    -记录
     * zan2    +记录
     * zan 5（+1 回复赞）  zan6  （-1回复赞）
     *加则true
     * 取消则false
     * @param pageData
     * @return
     * @throws Exception
     */
    public Map<String,Object> zan(PageData pageData) throws Exception {
        Map<String,Object> resultmap= new HashMap<>();
        if(dao.findForObject("videoMapper.zan", pageData)==null){
            //加记录 ，加数字
            dao.save("videoMapper.addzan2",pageData);
           dao.update("videoMapper.addzan1",pageData);
            resultmap.put("message",true);
            return resultmap;
        }else{
            //删记录 ，减数字
            dao.update("videoMapper.addzan4",pageData);
            dao.update("videoMapper.addzan3",pageData);
            resultmap.put("message",false);
          return resultmap;
        }
    }

    public Map<String,Object> replyZan(PageData pageData) throws Exception {
        Map<String, Object> resultmap = new HashMap<>();
        if (dao.findForObject("videoMapper.zan7", pageData)==null) {
            //加记录 ，加数字
            dao.update("videoMapper.addzan8", pageData);
            dao.update("videoMapper.addzan5", pageData);

            resultmap.put("message", true);
            return resultmap;
        }else {
            //删记录 ，减数字
            dao.update("videoMapper.addzan9", pageData);
            dao.update("videoMapper.addzan6", pageData);

            resultmap.put("message", false);
            return resultmap;
        }
    }






    /**
     * 查看某条评论的所有回复
     * @param pageData
     * @return
     * @throws Exception
     */
    public List<PageData> showAllContentReply(PageData pageData) throws Exception {
        List<PageData> pageDataList=null;
        List<PageData> pageDataList1=null;
        pageDataList=(List<PageData>)dao.findForList("videoMapper.showAllContentReply",pageData);
        return pageDataList;
    }


    /**
     * 添加某个视频的评论
     * @param pageData
     * @return
     * @throws Exception
     */
    public int addVideoContent(PageData pageData) throws Exception {
        return (int)dao.save("videoMapper.addVideoContent",pageData);
    }

    /**
     * 添加视频回复 reply
     * @param pageData
     * @return
     * @throws Exception
     */
    public int addVideoContentReply(PageData pageData) throws Exception {
        int save1 = (int) dao.save("videoMapper.addVideoContentReply", pageData);
        int sava2 = (int) dao.save("videoMapper.addVideoContentReplyNum", pageData);
        int sava = sava2 + save1;
        return sava;
    }

    /**
     * 用户自己删除自己的评论
     * @param pageData
     * @return
     * @throws Exception
     */
    public int deleteContentById(PageData pageData) throws Exception {
        String id = pageData.getString("plid");
        pageData.put("id",id);  //转plid—>id
        List<PageData> select = (List<PageData>) dao.findForList("videoMapper.showAllContentReply", pageData);
        if(select.size()!=0){   //有回复则先删除回复,再删除评论
            dao.delete("videoMapper.deleteReplyById",pageData);
            dao.delete("videoMapper.deleteContentById",pageData);
            return 1;
        }else{
            dao.delete("videoMapper.deleteContentById",pageData);
            return 1;
        }
    }


    /**
     * 用户自己删除自己的回复
     * @param pageData
     * @return
     * @throws Exception
     */
    public int deleteOneReplyById(PageData pageData) throws Exception {
        //删除回复，对应的评论数减一
        dao.delete("videoMapper.deleteOneReplyById",pageData);
        dao.update("videoMapper.updateReplyNum",pageData);
        return 1;
    }

}
