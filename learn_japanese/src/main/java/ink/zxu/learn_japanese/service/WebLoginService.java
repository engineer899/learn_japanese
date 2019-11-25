package ink.zxu.learn_japanese.service;

import ink.zxu.learn_japanese.dao.DaoSupport;
import ink.zxu.learn_japanese.entity.SysUser;
import ink.zxu.learn_japanese.utils.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author 张伟
 * @date 2019/11/13 16:27
 */
@Service
public class WebLoginService {
    @Resource(name = "daoSupport")
    private DaoSupport dao;

    public SysUser findByUsername(String username) throws Exception {
        return (SysUser) dao.findForObject("webLoginMapper.findByUsername",username);
    }

    public List<PageData> findRoleByUser(String uid) throws Exception{
        return (List<PageData>) dao.findForList("webLoginMapper.findRoleByUser",uid);
    }

    public List<PageData> findPermissionByRole(String rid) throws Exception{
        return (List<PageData>) dao.findForList("webLoginMapper.findPermissionByRole",rid);
    }

    public List<PageData> findPermissionByUser(String uid) throws Exception{
        List<PageData> roleList=findRoleByUser(uid);
        List<PageData> PermissionList=null;
        for (PageData role:roleList){
            PermissionList.addAll(findPermissionByRole(role.getString("rid")));
        }
        return PermissionList;
    }



}
