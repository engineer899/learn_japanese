package ink.zxu.learn_japanese.component.shrio;

import ink.zxu.learn_japanese.entity.SysPermission;
import ink.zxu.learn_japanese.entity.SysRole;
import ink.zxu.learn_japanese.entity.SysUser;
import ink.zxu.learn_japanese.service.WebLoginService;
import ink.zxu.learn_japanese.utils.PageData;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 张伟
 * @date 2019/11/13 14:49
 */
public class ShrioRealm extends AuthorizingRealm {

    @Resource
    private WebLoginService webLoginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser userInfo  = (SysUser)principals.getPrimaryPrincipal();
        List<PageData> roleList=null;
        List<PageData> permissionList=null;
        try{
            roleList =webLoginService.findPermissionByRole(userInfo.getUid());
            permissionList=webLoginService.findPermissionByUser(userInfo.getUid());
        }catch(Exception e){

        }
        for(PageData role:roleList){
            authorizationInfo.addRole(role.getString("role"));
        }
        for(PageData permission:permissionList){
            authorizationInfo.addStringPermission(permission.getString("url"));
        }
//        CacheUntil.setCacheTree(authorizationInfo);
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("ShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUser userInfo = null;
        try {
            userInfo = webLoginService.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(userInfo == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户信息
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return null;
    }
}
