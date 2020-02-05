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
 * 公告service层
 */
@Service
public class AnnService {
    @Resource(name="daoSupport")
    private DaoSupport dao;
    /**
     * 公告分页查询
     * @param page
     * @return
     * @throws Exception
     */
    public List<PageData> queryAnnInfoListPage(Page page) throws Exception {
        return (List<PageData>) dao.findForList("annMapper.queryAnnInfoListPage", page);
    }

    /**
     * 删除公告
     * @param pageData
     * @return
     * @throws Exception
     */
    public int deleteAnn(PageData pageData) throws Exception {
        return (int) dao.delete("annMapper.deleteAnn",pageData);
    }

    /**
     * 添加公告
     * @param pageData
     * @return
     * @throws Exception
     */
    public int addAnn(PageData pageData) throws Exception {
        return (int)dao.save("annMapper.addAnn",pageData);
    }

    /**
     * 查询公告by id
     * @param pageData
     * @return
     * @throws Exception
     */
    public PageData queryAnnById(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("annMapper.queryAnnById", pageData);
    }

    /**
     * 查询所有公告
     * @param pageData
     * @return
     * @throws Exception
     */
    public List<PageData> showAllAnnouncement(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("annMapper.showAllAnnouncement", pageData);
    }
}
