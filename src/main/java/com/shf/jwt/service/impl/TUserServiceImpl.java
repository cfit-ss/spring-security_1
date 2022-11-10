package com.shf.jwt.service.impl;

import com.shf.jwt.dao.TUserDao;
import com.shf.jwt.entity.TUser;
import com.shf.jwt.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author: shengs
 * @date: 2022/11/9
 * @version: V1.0
 */
@Service
public class TUserServiceImpl implements TUserService {
    @Autowired
    private TUserDao userDao;

    @Override
    public TUser findByName(String username) {
        return userDao.findByName(username);
    }
}
