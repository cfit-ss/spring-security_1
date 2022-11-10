package com.shf.jwt.service;


import com.shf.jwt.entity.TUser;

/**
 * 描述：用户表服务类
 * @author: shengs
 * @date: 2022-11-08
 * @version: V1.0
 */
public interface TUserService{
    /**
     * @param username
     * @return
     */
    public TUser findByName(String username);
}
