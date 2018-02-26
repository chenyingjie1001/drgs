package com.firesoon.drgs.shiro.cas;

import java.util.List;

import javax.annotation.PostConstruct;

import com.firesoon.drgs.dto.user.User;
import com.firesoon.drgs.services.user.UserSerivce;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author create by yingjie.chen on 2018/1/8.
 * @version 2018/1/8 14:09
 */
public class MyShiroCasRealm extends CasRealm{

    private static final Logger logger = LoggerFactory.getLogger(MyShiroCasRealm.class);

    @Autowired
    private UserSerivce userSerivce;

//    @PostConstruct
//    public void initProperty(){
////      setDefaultRoles("ROLE_USER");
//        setCasServerUrlPrefix(ShiroCasConfiguration.casServerUrlPrefix);
//        // 客户端回调地址
//        setCasService(ShiroCasConfiguration.shiroServerUrlPrefix + ShiroCasConfiguration.casFilterUrlPattern);
//    }

    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     * @see ：本例中该方法的调用时机为需授权资源被访问时
     * @see ：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * @see ：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        logger.info("权限验证成功");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)  {
        AuthenticationInfo authc = null;
        try {
            authc = super.doGetAuthenticationInfo(token);

        }catch (Exception e ){
            e.printStackTrace();
        }
        if(authc != null) {
            String account = (String) authc.getPrincipals().getPrimaryPrincipal();
            if(account != null) {
                //保存当前用户信息
                User user = userSerivce.findUserByLogin(account);
                Session session = SecurityUtils.getSubject().getSession();
                session.setAttribute("user", user);
            }
        }
        return authc;
    }
}

