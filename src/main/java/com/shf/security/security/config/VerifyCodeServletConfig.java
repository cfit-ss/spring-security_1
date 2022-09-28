package com.shf.security.security.config;

import com.shf.security.security.utils.VerifyCodeFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：验证码servlet配置项
 *
 * @Author shengs
 * @Version V1.0
 **/
//@Configuration
public class VerifyCodeServletConfig {
    @Bean
    public ServletRegistrationBean indexServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new VerifyCodeFactory());
        registration.addUrlMappings("/getVerifyCode");
        return registration;
    }
}
