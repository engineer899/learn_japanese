package ink.zxu.learn_japanese.service;

import ink.zxu.learn_japanese.dao.DaoSupport;
import ink.zxu.learn_japanese.utils.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 张伟
 * @date 2019/9/15 10:35
 */

@Service
public class LoginService {
    @Resource(name = "daoSupport")
    private DaoSupport dao;


    public int setLogin(PageData pageData) throws Exception {
        if(dao.findForObject("loginMapper.findUser",pageData)!=null){
            int a= (int)dao.update("loginMapper.updateUser",pageData);
            System.out.println(a);
            return a;
        }else{
            int a= (int)dao.save("loginMapper.addUser",pageData);
            System.out.println(a);
            return a;
        }
    }

    public int userCount() throws Exception {
        return (int)dao.findForObject("loginMapper.userCount");
    }

}
