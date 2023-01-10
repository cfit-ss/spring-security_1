package com.shf.jwt.security.role;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 描述：
 *
 * @Author shengs
 * @Date 2023/01/10
 * @Version V1.0
 **/

//---------生成customerAccessSecisionManager----
    /**
public class CustomAccessDecisionManager implements  AccessDecisionmanager{
      public void decide(Authentication authentication,Object o,Collection<ConfigAttribute> collection) throws AccessDeniedException,InsufficientAuthenticatonException{
          HttpservletRequest request=((FilterInvocation)o).getHttpRequest();
          String url;
          for(GrantedAuthority ga:authentication.getAuthorities()){
           url =ga.getAutjority();
           if(url.euqals(request.getrequestURI())){
         return;
           }
          }
          //抛出异常exception
           throw new AccessDiniedException("没有权限访问");
      }
     public boolean supports(ConfigAttribute configAttribute){return true;}
     public boolean supports(Class<?> aclass){return  true;}
}
    //------------
     */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    /**
     * 判定是否拥有权限的决策方法
     * @param authentication CustomUserDetailsService类loadUserByUsername()方法中返回值
     * @param o 包含客户端发起的请求的request信息。
     * @param collection CustomFilterInvocationSecurityMetadataSource类的getAttribute()方法返回值
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        String url;
        for (GrantedAuthority ga : authentication.getAuthorities()) {
             url = ga.getAuthority();
             if(url.equals(request.getRequestURI())){
                return;
             }
        }
        throw new AccessDeniedException("没有权限访问");
    }
    //参数字段
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
//public static void main(){
//}