package com.shf.jwt.utils;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author shengs
 * @Date 2022/11/11
 * @Version V1.0
 **/
public class RequestUtil {
    public static boolean matchers(String url, HttpServletRequest request) {
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
        if (matcher.matches(request)) {
            return true;
        }
        return false;
    }
}
