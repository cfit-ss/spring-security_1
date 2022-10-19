package com.shf.security.utils;

import com.shf.security.user.entity.TUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 描述：
 *
 * @Author shengs
 * @Date 2022/10/17
 * @Version V1.0
 **/
public class UserHolder {
    public static TUser getUserDetail(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        TUser user = (TUser) auth.getPrincipal();
        return user;
    }
    public static String getUserCode(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        TUser user = (TUser) auth.getPrincipal();
        return user.getCode();
    }
    public static int getUserId(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        TUser user = (TUser) auth.getPrincipal();
        return user.getId();
    }
}
