package com.firesoon.drgs.services.user.impl;

import com.firesoon.drgs.dto.user.User;
import com.firesoon.drgs.mapper.user.UserMapper;
import com.firesoon.drgs.services.user.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author create by yingjie.chen on 2017/10/11.
 * @version 2017/10/11 15:28
 */

@Service
public class UserSerivceImpl implements UserSerivce {


    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> findUser(User user) {
        return mapper.find(user);
    }

    @Override
    public User findUserByLogin(String loginname) {
        return mapper.findUserByLogin(loginname);
    }

}
