package com.shf.jwt.security.role;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;


/**
 * 描述：
 *
 * @Author shengs
 * @Date 2023/1/31
 * @Version V1.0
 **/
//------------------coding-signature--------------

/***
 *  生成security 封装
 */
//public class CustomerFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter{
  //      private FilterInvocationSecurityMetadataSource securityMetadataSource;//
    //    public void setCustomAccessDecisionManager(CustomAccessDecisionManager  customAccessDecisionManager){
      //      super.setAccessDecisionManager(customAccessDecisionManager);
        //}
    //public void init(FilterConfig filterConfig) throws ServletException{
//---------------encode------------------------------------
@Component
@Slf4j
public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;
    @Autowired
    public void setCustomAccessDecisionManager(CustomAccessDecisionManager customAccessDecisionManager) {
        super.setAccessDecisionManager(customAccessDecisionManager);
    }
    //
    /**
     * 登录后，每次发起请求都会经过该过滤器
     * @param request
     * @param response
     * @param chain
     * @throws IOException
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }
    /*
    //增加invoke字段  作为拦截的url--参
    protected void invoke(filterInvocation fi) throws IOExceptin,ServletException{
        //fi拦截url字段
        //CustomerFilterInvocationSecurityMetaSource ---getAttributes(Object object)
        InterceptorStatusToken token =super.beforeInvocation(fi);
        try{
            //执行拦截器
            fi.getChain().doFilter(fi.getRequest,fi.getResponse());
        }
        finally{
            super.afterInvocation(token,null);
        }

        }

    }*/

    protected void invoke(FilterInvocation fi) throws IOException,ServletException{
        Interceptor
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        //fi里面有一个被拦截的url
        //里面调用CustomFilterInvocationSecurityMetadataSource的getAttributes(Object object)这个方法判断该请求是否需要进行角色判断
        //也就是CustomAccessDecisionManager类的decide方法
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            //执行下一个拦截器

            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }
    @Override
    public void destroy() {
    }
    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }
    /*
    private Class<?> getSecureObjectClass(){
       String l1;
       String l2;
       char r1;
       char r2;
       decimal  k1;
       decimal k2;
       private
    }*/
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
}

public Security_flag getSecurityClass(){
        String buffer_1=flag;

}


//private Security_1  getSecurityClass(){
//}