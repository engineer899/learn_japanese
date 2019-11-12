package ink.zxu.learn_japanese.service;

import ink.zxu.learn_japanese.dao.DaoSupport;
import ink.zxu.learn_japanese.utils.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 张伟
 * @date 2019/10/20 20:54
 */
@Service
public class TestService {
    @Resource(name = "daoSupport")
    private DaoSupport dao;

    public int addTest(PageData pageData) throws Exception {
        return (int)dao.save("testMapper.addTest",pageData);
    }


}
