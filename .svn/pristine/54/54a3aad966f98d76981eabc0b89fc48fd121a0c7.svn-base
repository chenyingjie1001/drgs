package com.firesoon.drgs.shiro;

import com.firesoon.drgs.shiro.filter.ShiroLoginFilter;
import com.firesoon.drgs.shiro.filter.ShiroPermsFilter;
import com.firesoon.drgs.shiro.filter.ShiroRolesFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * @author create by yingjie.chen on 2017/10/11.
 * @version 2017/10/11 16:43
 */
//@Configuration
public class ShiroConfiguration {
    //配置核心安全事务管理器
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        System.out.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    @Bean(name = "shiroRolesFilter")
    public ShiroRolesFilter getShiroRolesFilter(){
        return new ShiroRolesFilter();
    }

    @Bean(name = "shiroLoginFilter")
    public ShiroLoginFilter getShiroLoginFilter(){
        return new ShiroLoginFilter();
    }

    @Bean(name = "shiroPermsFilter")
    public ShiroPermsFilter getShiroPermsFilter(){
        return new ShiroPermsFilter();
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/");
        bean.setSuccessUrl("/");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        filterChainDefinitionMap.put("/*.html", "anon"); //表示可以匿名访问
//        filterChainDefinitionMap.put("/webjars/*", "anon");
//        filterChainDefinitionMap.put("/logout*","anon");
//        filterChainDefinitionMap.put("/jsp/error.jsp*","anon");
//        filterChainDefinitionMap.put("/jsp/index.jsp*","authc");
//        filterChainDefinitionMap.put("/*", "authc");//表示需要认证才可以访问
//        filterChainDefinitionMap.put("/**", "authc");//表示需要认证才可以访问
//        filterChainDefinitionMap.put("/*.*", "authc");
        filterChainDefinitionMap.put("/lonin*", "anon");
        filterChainDefinitionMap.put("/logOut*", "anon");
        filterChainDefinitionMap.put("/webservice/**", "anon");
//        filterChainDefinitionMap.put("/webservice/**", "authc");
        filterChainDefinitionMap.put("/user/**", "authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        LinkedHashMap<String, Filter> filterLinkedHashMap = new LinkedHashMap<String, Filter>();
        filterLinkedHashMap.put("shiroPermsFilter", getShiroPermsFilter());
        filterLinkedHashMap.put("shiroLoginFilter", getShiroLoginFilter());
        filterLinkedHashMap.put("shiroRolesFilter", getShiroRolesFilter());
        bean.setFilters(filterLinkedHashMap);
        return bean;
    }

    //配置自定义的权限登录器
    @Bean(name = "authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    //配置自定义的密码比较器
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
}
