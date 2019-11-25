package ink.zxu.learn_japanese.config;

import ink.zxu.learn_japanese.component.shrio.ShrioRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 张伟
 * @date 2019/11/13 15:03
 */
@Configuration
public class ShrioConfig {
    @Bean(name = "shrioRealm")
    public ShrioRealm getShrioRealm(){
        ShrioRealm realm=new ShrioRealm();
        return realm;
    }

    @Bean(name = "securityManagerBean")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shrioRealm")ShrioRealm realm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManagerBean")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/manage/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();

        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
