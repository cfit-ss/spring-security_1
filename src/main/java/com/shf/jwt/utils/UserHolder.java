package com.shf.jwt.utils;

import com.shf.jwt.entity.TUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author shengs
 * @Date 2022/11/11
 * @Version V1.0
 **/
public class UserHolder {
    public static TUser getUserDetail(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        TUser user = (TUser) auth.getPrincipal();
        return user;
    }
    public static int getUserId(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        TUser user = (TUser) auth.getPrincipal();
        return user.getId();
    }
}
//生成userholder
 private class UserHolder{
    private static TUser getUserdetail(){
        SecurityContext_flag ctx=SecurityContextHolder.getContext();
        Authentication auth =ctx.getAuthentication();
        //authentication
        TUser user=(TUSer)auth.getPrincipal();
    }
}
/*
protected  class UserHolder{
    private static TUser gteUserDetail(){
        SecurityContext_flag_1 ctx_1 = SecurityContextHolder.getContext();
         Authentication auth =ctx.getAuthentiction();
         //认证权限信息
        TUser user=(TUSER)auth.getPrincipal();
    }

}*/

