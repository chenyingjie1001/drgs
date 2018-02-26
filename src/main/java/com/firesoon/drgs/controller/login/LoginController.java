package com.firesoon.drgs.controller.login;

import com.alibaba.fastjson.JSONObject;
import com.firesoon.drgs.controller.base.BaseController;
import com.firesoon.drgs.dto.base.ResultMessage;
import com.firesoon.drgs.dto.user.User;
import com.firesoon.drgs.shiro.cas.ShiroCasConfiguration;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author create by yingjie.chen on 2017/10/11.
 * @version 2017/10/11 17:06
 */
@Api
@RestController
@RequestMapping(method = RequestMethod.POST)
public class LoginController extends BaseController {


    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation(value = "login", notes = "login")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody User u){
        ResultMessage<String> resultMessage = new ResultMessage<String>();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(u.getLogin_name(),u.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);   //完成登录
            Object user = subject.getPrincipal();
            subject.getSession().setAttribute("user", user);
            //设置会话的过期时间--ms,默认是30分钟，设置负数表示永不过期
            return success();
        } catch(Exception e) {
            throw new AuthenticationException("登陆失败");
        }
    }
    @ApiOperation(value = "logout", notes = "logOut")
    @GetMapping(value = "/logout")
    public Object logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        subject.getSession().removeAttribute("user");
//        return success();
        String redirectUrl = ShiroCasConfiguration.casLogoutUrl + "?service=" + ShiroCasConfiguration.successUrl;
        ResultMessage<String> rm = new ResultMessage<String>();
        rm.setMsg("退出登录");
        rm.setHttpCode(401);
        rm.setUrl(redirectUrl);
        return rm;
    }
}