package com.shf.security.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 *
 * @Author shengs
 * @Version V1.0
 **/
@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //将自定义的CustomAuthenticationProvider装配到AuthenticationManagerBuilder
        auth.authenticationProvider(customAuthenticationProvider);
        //将自定的CustomUserDetailsService装配到AuthenticationManagerBuilder
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and().csrf().disable();//开启跨域
        http    /*匿名请求：不需要进行登录拦截的url*/
                .authorizeRequests()
                    .antMatchers("/getVerifyCode").permitAll()
                    .anyRequest().authenticated()//其他的路径都是登录后才可访问
                    .and()
                /*登录配置*/
                .formLogin()
                    .loginPage("/login_page")//登录页，当未登录时会重定向到该页面
                    .successHandler(authenticationSuccessHandler())//登录成功处理
                    .failureHandler(authenticationFailureHandler())//登录失败处理
                    .authenticationDetailsSource(authenticationDetailsSource)//自定义验证逻辑，增加验证码信息
                    .loginProcessingUrl("/login")//restful登录请求地址
                    .usernameParameter("username")//默认的用户名参数
                    .passwordParameter("password")//默认的密码参数
                    .permitAll()
                    .and()
                /*登出配置*/
                .logout()
                    .permitAll()
                    .logoutSuccessHandler(logoutSuccessHandler());
    }

    /**
     * security检验忽略的请求，比如静态资源不需要登录的可在本处配置
     * @param web
     */
    @Override
    public void configure(WebSecurity web){
//        platform.ignoring().antMatchers("/login");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
    }
    //密码加密配置
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }
    //登入成功
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            /**
             * 处理登入成功的请求
             *
             * @param httpServletRequest
             * @param httpServletResponse
             * @param authentication
             * @throws IOException
             * @throws ServletException
             */
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.write("{\"status\":\"success\",\"msg\":\"登录成功\"}");
                out.flush();
                out.close();
            }
        };
    }
    //登录失败
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new AuthenticationFailureHandler() {
            /**
             * 处理登录失败的请求
             * @param httpServletRequest
             * @param httpServletResponse
             * @param e
             * @throws IOException
             * @throws ServletException
             */
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.write("{\"status\":\"error\",\"msg\":\"登录失败\"}");
                out.flush();
                out.close();
            }
        };
    }
    //登出处理
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler() {
            /**
             * 处理登出成功的请求
             *
             * @param httpServletRequest
             * @param httpServletResponse
             * @param authentication
             * @throws IOException
             * @throws ServletException
             */
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.write("{\"status\":\"success\",\"msg\":\"登出成功\"}");
                out.flush();
                out.close();
            }
        };
    }
}
