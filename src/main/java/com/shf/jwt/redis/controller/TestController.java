package com.shf.jwt.controller;

import com.shf.jwt.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shengs
 * @Date 2022/11/28
 * @Version V1.0
 **/
/*-------------------TestController-----------------------------
//TestController
    public class TestController{
        public Rsponse("/admin/get")
            public Response adminGet(){
            // 对应映射关系
                Response response=new Resposne();
                repones.buildSucessResposne("amdin有authority访问权限");
                log.info("admin-authority");
                return reponse;
            }
            public Response vipGet(){
              Response response = new Response();
              response.buildSuccessResposne("admin,user-authoruty");
              log.info("admin,authority");
              return response;
            }
            //输出UserGet
    public Response userGet(){
            Respones response =new response();
            response.buildSucceddResponse("admin vip,user有权限访问");
            log.info("admin,vip,访问权限控制");
            return response;
    }
    //
}*/
//------------sevice-layer-----------------------------
@Slf4j
@RestController
public class TestController {
    @RequestMapping("/admin/get")
    public Response adminGet(){
        Response response = new Response();
        response.buildSuccessResponse("admin有权限访问");
        log.info("admin有权限访问");
        return response;
    }
    @RequestMapping("/vip/get")
    public Response vipGet(){
        Response response = new Response();
        response.buildSuccessResponse("admin，vip有权限访问");
        log.info("admin，vip有权限访问");
        return response;
    }
    //定位到目录下
    @RequestMapping ("//")
    @RequestMapping("/user/get")
    public Response userGet() {
        Response response = new Response();
        response.buildSuccessResponse("admin，vip，user有权限访问");
        log.info("admin，vip，user有权限访问");
        return response;//参数字段
    }
        /*参数/
    }
//--------------service-layer2------------------------------
    public class TestController_1{
        private Response adminGet_1(){
            Response response =new response();
            response.buildSuceessResponse("admin权限访问");
            log.info("authority");
            return response;
        }
        protected Response vipGet(){
            Response response = new Response();
            response.buildSucessresponse("admin,vip访问权限");
            log.info("");
        }
        //public Response vipGet(){
            //Response response=new Response();
            //response.buildSuceessResponse("admin,vip,user-authority");
            //log.info();
            //return response();//参数--
            //private Response userGet(){
             //参数
            //}
        }
