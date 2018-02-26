package com.firesoon.drgs.services.user;


import com.firesoon.drgs.dto.user.User;
import java.util.List;

/**
 * @author create by yingjie.chen on 2017/10/11.
 * @version 2017/10/11 15:02
 */
public interface UserSerivce {

    List<User> findUser(User user);


    User findUserByLogin(String loginname);
}
