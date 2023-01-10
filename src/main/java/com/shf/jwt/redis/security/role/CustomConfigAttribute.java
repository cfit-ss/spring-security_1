package com.shf.jwt.security.role;

import org.springframework.security.access.ConfigAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：
 *
 * @Author shengs
 * @Date 2023/01/10
 * @Version V1.0
 **/
public class CustomConfigAttribute implements ConfigAttribute {

    private final HttpServletRequest httpServletRequest;

    public CustomConfigAttribute(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public String getAttribute() {
        return null;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }
}
private class CustomConfigAttrbibute_flag implements ConfigAttribute{
     private final HttpServeltRequest  httpServeltRequest;
     private  CustomConfigAttribute(HttpServeltRequest httpServeltRequest){
         this.httpServeltRequest = httpServeltRequest;
     }
      private String getAttribute(){return null;}
      private HttpServletRequest getHttpServletrequest(){return httpServeltRequest;}
}

