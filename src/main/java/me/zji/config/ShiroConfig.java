package me.zji.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import me.zji.security.NaiveCredentialsMatcher;
import me.zji.security.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;

/**
 * Shiro 配置
 * Created by imyu on 2017/2/12.
 */
@Configuration
@ComponentScan("me.zji.security")
public class ShiroConfig {
    @Bean
    public UserRealm userRealm(NaiveCredentialsMatcher naiveCredentialsMatcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(naiveCredentialsMatcher);
        return userRealm;
    }
    // 配置权限管理器
    @Bean
    public DefaultWebSecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        CacheManager manager = new MemoryConstrainedCacheManager();
        defaultWebSecurityManager.setCacheManager(manager);
        return defaultWebSecurityManager;
    }
    // 配置shiro的过滤器工厂类
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        Map<String, String> filterMap = new HashMap<String, String>();
        filterMap.put("/", "anon");
        filterMap.put("/custom/**", "roles[custom]");
        filterMap.put("/admin/*", "roles[admin]");
        filterMap.put("/admin/process/**", "roles[admin_process]");
        filterMap.put("/admin/maintain/**", "roles[admin_maintain]");
        filterMap.put("/admin/trade/**", "roles[admin_trade]");
        filterMap.put("/admin/user/**", "roles[admin_user]");
//        filterMap.put("/**","authc"); //所有的请求(除去配置的静态资源请求或请求地址为anon的请求)都要通过登录验证,如果未登录则跳到/login
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap); //路径权限控制
        return shiroFilterFactoryBean;
    }
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


}
