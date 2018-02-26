package com.firesoon.drgs.Utils;

import com.firesoon.drgs.dto.user.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;

/**
 * @author create by yingjie.chen on 2017/12/7.
 * @version 2017/12/7 16:32
 */
public class UserUtils {

    public static User getUserMsg(){
        Object obj = SecurityUtils.getSubject().getPrincipal();
        return (User) obj;
    }

    public static User getUser(){
        Session session = SecurityUtils.getSubject().getSession();
        return (User) session.getAttribute("user");
    }
}
