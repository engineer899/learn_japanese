package ink.zxu.learn_japanese.service;

import ink.zxu.learn_japanese.dao.DaoSupport;
import ink.zxu.learn_japanese.utils.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangwei
 * @date 2020/2/9 17:59
 */
@Service
public class KnowService {
    @Resource(name="daoSupport")
    private DaoSupport dao;







    /**
     *查询回复数
     * @return
     * @throws Exception
     */
    public Integer queryReplyNum(PageData pageData) throws Exception {
        return (Integer) dao.findForObject("knowMapper.queryReplyNum", pageData);
    }

    /**
     * 查询点赞数
     * @return
     * @throws Exception
     */
    public Integer queryZanNum(PageData pageData) throws Exception {
        return (Integer) dao.findForObject("knowMapper.queryZanNum", pageData);
    }
    /**
     * 查询点赞数
     * @return
     * @throws Exception
     */
    public Integer queryCommentNumByArtId(PageData pageData) throws Exception {
        return (Integer) dao.findForObject("knowMapper.queryCommentNumByArtId", pageData);
    }

    /**
     * 查询评论是否存在
     * @return
     * @throws Exception
     */
    public Integer queryCommentNumById(PageData pageData) throws Exception {
        return (Integer) dao.findForObject("knowMapper.queryCommentNumById", pageData);
    }

    /**
     * 查询回复是否存在
     * @return
     * @throws Exception
     */
    public Integer queryReplyNumById(PageData pageData) throws Exception {
        return (Integer) dao.findForObject("knowMapper.queryReplyNumById", pageData);
    }


    /**
     * 查询评论
     * @return
     * @throws Exception
     */
    public List<PageData> queryComment(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("knowMapper.queryComment", pageData);
    }
    /**
     * 查询回复
     * @return
     * @throws Exception
     */
    public PageData queryIsZan(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("knowMapper.queryIsZan", pageData);
    }

    /**
     * 查询回复
     * @return
     * @throws Exception
     */
    public List<PageData> queryReply(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("knowMapper.queryReply", pageData);
    }



    /**
     * 更新点赞
     * @return
     * @throws Exception
     */
    public Integer updateZan(PageData pageData) throws Exception {
        return  (Integer) dao.save("knowMapper.updateZan",pageData);
    }

    /**
     * 点赞
     * @return
     * @throws Exception
     */
    public Integer addZan(PageData pageData) throws Exception {
        return  (Integer) dao.save("knowMapper.addZan",pageData);
    }

    /**
     * 添加评论
     * @return
     * @throws Exception
     */
    public Integer addComment(PageData pageData) throws Exception {
        return  (Integer) dao.save("knowMapper.addComment",pageData);
    }
    /**
     * 添加回复
     * @return
     * @throws Exception
     */
    public Integer addReply(PageData pageData) throws Exception {
        return  (Integer) dao.save("knowMapper.addReply",pageData);
    }




    /**
     * 类型列表查询
     * @return
     * @throws Exception
     */
    public List<PageData> queryTypeList(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("knowMapper.queryTypeList", pageData);
    }


    /**
     * 类型列表查询
     * @return
     * @throws Exception
     */
    public List<PageData> queryKnowByID(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("knowMapper.queryKnowByID", pageData);
    }


    /**
     * 类型总数
     * @return
     * @throws Exception
     */
    public PageData queryTypeCount(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("knowMapper.queryTypeCount", pageData);
    }



    /**
     * 查询类型是否存在
     * @return
     * @throws Exception
     */
    public List<PageData>  queryTypeByName(PageData pageData) throws Exception {
        return (List<PageData> ) dao.findForList("knowMapper.queryTypeByName", pageData);
    }


    /**
     * 添加类型
     * @return
     * @throws Exception
     */
    public Integer addType(PageData pageData) throws Exception {
        return  (Integer) dao.save("knowMapper.addType",pageData);
    }



    /**
     * 知识点列表查询
     * @return
     * @throws Exception
     */
    public List<PageData> queryKnowList(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("knowMapper.queryKnowList", pageData);
    }

    /**
     * 知识点总数
     * @return
     * @throws Exception
     */
    public PageData queryKnowCount(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("knowMapper.queryKnowCount", pageData);
    }



    /**
     * 知识点是否存在
     * @return
     * @throws Exception
     */
    public List<PageData>  queryKnowByName(PageData pageData) throws Exception {
        return (List<PageData> ) dao.findForList("knowMapper.queryKnowByName", pageData);
    }


    /**
     * 添加知识点
     * @return
     * @throws Exception
     */
    public Integer addKnow(PageData pageData) throws Exception {
        return  (Integer) dao.save("knowMapper.addKnow",pageData);
    }

}
