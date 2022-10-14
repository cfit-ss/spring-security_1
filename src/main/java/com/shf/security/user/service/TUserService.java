package com.shf.security.user.service;

import com.shf.security.user.entity.TUser;

/**
 * 描述：用户表服务类
 * @author: shengs
 * @date: 2022-10-13
 * @version: V1.0
 */
public interface TUserService{
    /**
     * @param username
     * @return
     */
    public TUser findByName(String username);
}
