package com.shf.security.user.controller;

import com.shf.security.user.entity.TUser;
import com.shf.security.user.service.TUserService;
import com.shf.security.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：用户controller
 *
 * @author: shengs
 * @date: 2022-10-13
 * @version: V1.0
 */
@RestController
@RequestMapping("/user")
public class TUserController{
    @Autowired
    private TUserService userService;

    @RequestMapping("/findOne")
    public TUser findOne(String username){
        return userService.findByName(username);
    }

    @RequestMapping("/test")
    public Response test(String username){
        Response response = new Response();
        return response.buildSuccessResponse();
    }
}


