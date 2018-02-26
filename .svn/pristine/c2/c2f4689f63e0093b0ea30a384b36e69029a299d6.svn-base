package com.firesoon.drgs.shiro;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author create by yingjie.chen on 2017/10/13.
 * @version 2017/10/13 15:08
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)

        String inPassword = new String(utoken.getPassword());
        String md5Password = DigestUtils.md5Hex(inPassword);
        //获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        //进行密码的比对
        return md5Password.equals(dbPassword);
    }
}
