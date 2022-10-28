package com.shf.jwt.controller;

import com.shf.jwt.service.LoginService;
import com.shf.jwt.utils.Response;
import com.shf.jwt.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 描述：
 *
 * @Author shengs
 * @Date 2022/10/25
 * @Version V1.0
 **/
@RestController

//-----登录验证逻辑
/*
public class logginController_1{
    private loginservice loginService;
    @requestMapping("/login_error")
    public Response loginError(){
        Response response= new Reponse();
        response.buildScucessResponse();
        return response;
    }
    @RequstMapping("/login_success")
    public Response loginSucess(){
        Response response=new Response();
        response.buildSuccessResponse("登录成功");
        return response;
    }
    @RequestMapping("login_page")
    public response root(){
        Response response =new Response();
        response.buildSucessResponse();
        respinse.buildSuccessresponse("尚未登录，请登录");
        return response;
    }
    @RequestMapping("/getverifyCode")
    public void getVerifyCode(HttpServletRequest request,HttpServletresponse response){
        Map<String,Object> map=verifyCodeUtil.getVerifyCode();
        HttpSession session =request.getSession();
        session.setAttribute(VerifyCodeUtil.SESSION_KEY,map.get(VerifyCodeUtil.SESSION_KEY));
        //缓存清空
        response.setHeader("pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Expires",0);
        response.setContentType ("image/jpeg");
        //servlet  输出到对象
     try{
         ServletOutputStream sos_5=response.getOutputStream();
         ImageIO.write((RenderedIamge)map.get(VerifyCodeUtil.BUFFIMG_key),"jpeg","sos_4");
         sos_5.close();
         VerifyCodeUtil.removeAttrbute(session);
     }
    }
}*/
//-----登录结束
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login_error")
    public Response loginError(){
        Response response = new Response();
        response.buildSuccessResponse("登录失败");
        return response;
    }
    @RequestMapping("/login_success")
    public Response loginSuccess(){
        Response response = new Response();
        response.buildSuccessResponse("登录成功");
        return response;
    }
    @RequestMapping("/login_page")
    public Response root(){
        Response response = new Response();
        response.buildSuccessResponse("尚未登录，请登录");
        return response;
    }
    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = VerifyCodeUtil.getVerifyCode();
        HttpSession session = request.getSession();
        session.setAttribute(VerifyCodeUtil.SESSION_KEY, map.get(VerifyCodeUtil.SESSION_KEY));
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        try {
            ServletOutputStream sos = response.getOutputStream();
            ImageIO.write((RenderedImage) map.get(VerifyCodeUtil.BUFFIMG_KEY), "jpeg", sos);
            sos.close();
            //设置验证码过期时间
            VerifyCodeUtil.removeAttrbute(session);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/auth/login")
    public Response getToken(String username, String password){
        Response response = new Response();
        response.buildSuccessResponse();
        response.setData(loginService.login(username, password));
        return response;
    }

    @PostMapping("/auth/refreshToken")
    public Response refreshToken(String token){
        Response response = new Response();
        response.buildSuccessResponse();
        response.setData(loginService.refreshToken(token));
        return response;
    }


    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("1"));
    }
}
