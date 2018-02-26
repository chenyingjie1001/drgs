package com.firesoon.drgs.controller.user;

import com.firesoon.drgs.controller.base.BaseController;
import com.firesoon.drgs.dto.user.User;
import com.firesoon.drgs.services.user.UserSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by yingjie.chen on 2017/10/11.
 * @version 2017/10/11 15:05
 */

@RestController
@Api
@RequestMapping(value = "/user/", method = RequestMethod.POST)
public class UserController extends BaseController {


    @Autowired
    private UserSerivce userSerivce;


    @ApiOperation(value = "finduser", notes = "finduser")
    @RequestMapping(value = "findUser")
    public Object findUser(@RequestBody User user){

        return userSerivce.findUser(user);
    }
}
