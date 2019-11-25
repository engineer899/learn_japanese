package ink.zxu.learn_japanese.service;

import ink.zxu.learn_japanese.dao.DaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zw
 * @date 2019/11/14 9:41
 */
@Service
public class UserInfoService {
    @Resource(name="daoSupport")
    private DaoSupport dao;




}
