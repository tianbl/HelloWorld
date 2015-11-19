package com.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by baolei on 2015/11/19.
 */
@Controller
public class MyController {
    @RequestMapping("login") //用来处理前台的login请求
    private String hello(@RequestParam(value = "username", required = false)String username,
            @RequestParam(value = "password", required = false)String password){
        System.out.println("-----------------------" + "Hello "+username+",Your password is: "+password);
        return "/pages/helloworld";

    }

    @RequestMapping(value = "/getuser", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody User hellot(HttpServletRequest request, HttpServletResponse response){

        response.setHeader("asdfasdfas","asdfasdfasdfasdf");
        System.out.println("------------------------输出json数据");
        User user = new User();
        user.setUsername("tianbaolei");
        user.setPassword("tianbaolei");
        return user;
    }
}
