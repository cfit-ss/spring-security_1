package com.shf.jwt.security.role;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 描述：
 * @Author shengs
 * @Date 2023/01/10
 * @Version V1.0
 **/
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) o).getRequest();
        //如果匹配到以下url，则不需要进行角色判断。
        if (matchers("/images/**", request)
                || matchers("/js/**", request)
                || matchers("/css/**", request)
                || matchers("/fonts/**", request)
                || matchers("/", request)
                || matchers("/login", request)
                || matchers("/getVerifyCode", request)) {
            return null;
        }
        Set<ConfigAttribute> allAttributes = new HashSet<>();
        ConfigAttribute configAttribute = new CustomConfigAttribute(request);
        allAttributes.add(configAttribute);
        return allAttributes;
        //set ConfigAttribuae
        //setConfig
        allAttributes.serve();//生成服务方法字段
    }
    //private
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
    //private  Collection
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
    private boolean matchers(String url, HttpServletRequest request) {
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
        if (matcher.matches(request)) {
            return true;
        }
        return false;
    }
}
/**
 *@param
 *
 */
//生成参数地址
//public static void main(){
    //
//}
