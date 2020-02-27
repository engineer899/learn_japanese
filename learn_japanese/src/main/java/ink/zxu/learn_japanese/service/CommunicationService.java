package ink.zxu.learn_japanese.service;

import ink.zxu.learn_japanese.dao.DaoSupport;
import ink.zxu.learn_japanese.utils.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 张伟
 * @date 2019/9/22 12:48
 */
@Service
public class CommunicationService {
    @Resource(name = "daoSupport")
    private DaoSupport dao;

    public List<PageData> showDongtai() throws Exception {
        List<PageData> pageDataList = null;
        pageDataList = (List<PageData>) dao.findForList("communicationMapper.showContent");
        for (PageData pageData : pageDataList) {
            String images = pageData.getString("images");
            String[] list = images.split("@");
            pageData.put("list", list);
        }
        return pageDataList;
    }

    /**
     * 添加动态
     *
     * @return
     * @throws Exception
     */

    public int addContent(PageData pageData) throws Exception {
        return (int) dao.save("communicationMapper.addContent", pageData);
    }


    /**
     * 添加评论
     *
     * @return
     * @throws Exception
     */

    public int addComment(PageData pageData) throws Exception {
        return (int) dao.save("communicationMapper.addComment", pageData);
    }


    /**
     * 查询我是否对该评论点赞
     *
     * @return
     * @throws Exception
     */
    public PageData queryIsZan(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("communicationMapper.queryIsZan", pageData);
    }

    /**
     * 查询当前动态点赞数
     *
     * @return
     * @throws Exception
     */
    public Integer queryZanNum(PageData pageData) throws Exception {
        return (Integer) dao.findForObject("communicationMapper.queryZanNum", pageData);
    }

    /**
     * 查询动态评论数量
     *
     * @return
     * @throws Exception
     */
    public Integer queryCommentNumByDtId(PageData pageData) throws Exception {
        return (Integer) dao.findForObject("communicationMapper.queryCommentNumByDtId", pageData);
    }


    /**
     * 查询评论
     *
     * @return
     * @throws Exception
     */
    public List<PageData> queryComment(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("communicationMapper.queryComment", pageData);
    }


    /**
     * 查询回复
     *
     * @return
     * @throws Exception
     */
    public List<PageData> queryReply(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("communicationMapper.queryReply", pageData);
    }

    /**
     * 点赞
     *
     * @return
     * @throws Exception
     */
    public Integer addZan(PageData pageData) throws Exception {
        return (Integer) dao.save("communicationMapper.addZan", pageData);
    }

    /**
     * 更新点赞
     *
     * @return
     * @throws Exception
     */
    public Integer updateZan(PageData pageData) throws Exception {
        return (Integer) dao.save("communicationMapper.updateZan", pageData);
    }


    /**
     * 查询评论是否存在
     *
     * @return
     * @throws Exception
     */
    public Integer queryCommentNumById(PageData pageData) throws Exception {
        return (Integer) dao.findForObject("communicationMapper.queryCommentNumById", pageData);
    }

    /**
     * 查询回复数
     *
     * @return
     * @throws Exception
     */
    public Integer queryReplyNum(PageData pageData) throws Exception {
        return (Integer) dao.findForObject("communicationMapper.queryReplyNum", pageData);
    }

    /**
     * 添加回复
     *
     * @return
     * @throws Exception
     */
    public Integer addReply(PageData pageData) throws Exception {
        return (Integer) dao.save("communicationMapper.addReply", pageData);
    }

    /**
     * 删除动态
     *
     * @return
     * @throws Exception
     */
    public Integer deleteContent(PageData pageData) throws Exception {
        return (Integer) dao.delete("communicationMapper.deleteContent", pageData);
    }

    /**
     * 删除评论
     *
     * @return
     * @throws Exception
     */
    public Integer deleteComment(PageData pageData) throws Exception {
        return (Integer) dao.delete("communicationMapper.deleteComment", pageData);
    }

    /**
     * 删除回复
     *
     * @return
     * @throws Exception
     */
    public Integer deleteReply(PageData pageData) throws Exception {
        return (Integer) dao.delete("communicationMapper.deleteReply", pageData);
    }


    /**
     * 查询回复是否存在
     *
     * @return
     * @throws Exception
     */
    public Integer queryReplyNumById(PageData pageData) throws Exception {
        return (Integer) dao.findForObject("knowMapper.queryReplyNumById", pageData);
    }


    /**
     * 类型列表查询
     *
     * @return
     * @throws Exception
     */
    public List<PageData> queryTypeList(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("knowMapper.queryTypeList", pageData);
    }


    /**
     * 类型列表查询
     *
     * @return
     * @throws Exception
     */
    public List<PageData> queryKnowByID(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("knowMapper.queryKnowByID", pageData);
    }


    /**
     * 类型总数
     *
     * @return
     * @throws Exception
     */
    public PageData queryTypeCount(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("knowMapper.queryTypeCount", pageData);
    }


    /**
     * 查询类型是否存在
     *
     * @return
     * @throws Exception
     */
    public List<PageData> queryTypeByName(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("knowMapper.queryTypeByName", pageData);
    }


    /**
     * 添加类型
     *
     * @return
     * @throws Exception
     */
    public Integer addType(PageData pageData) throws Exception {
        return (Integer) dao.save("knowMapper.addType", pageData);
    }


    /**
     * 知识点列表查询
     *
     * @return
     * @throws Exception
     */
    public List<PageData> queryKnowList(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("knowMapper.queryKnowList", pageData);
    }

    /**
     * 知识点总数
     *
     * @return
     * @throws Exception
     */
    public PageData queryKnowCount(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("knowMapper.queryKnowCount", pageData);
    }


    /**
     * 知识点是否存在
     *
     * @return
     * @throws Exception
     */
    public List<PageData> queryKnowByName(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("knowMapper.queryKnowByName", pageData);
    }


    /**
     * 添加知识点
     *
     * @return
     * @throws Exception
     */
    public Integer addKnow(PageData pageData) throws Exception {
        return (Integer) dao.save("knowMapper.addKnow", pageData);
    }


}
