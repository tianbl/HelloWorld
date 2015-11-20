package com.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by baolei on 2015/11/19.
 */
@Controller
public class MyController {
    @RequestMapping("login") //
    private String hello(@RequestParam(value = "username", required = false)String username,
            @RequestParam(value = "password", required = false)String password){
        System.out.println("-----------------------" + "Hello "+username+",Your password is: "+password);
        return "/pages/helloworld";

    }

    @RequestMapping(value = "/getuser", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Map<String,String> hellot(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("asdfasdfas","asdfasdfasdfasdf");
        System.out.println("------------------------生成json");
        User user = new User();
        user.setUsername("tianbaolei");
        user.setPassword("tianbaolei");

        Map<String,String> map = new HashMap<String, String>();
        map.put("tianbaolei","tianbaolei");
        map.put("tianbaolei", "tianbaolei");

        return map;
    }

    @RequestMapping(value = "/getjson", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody List getJson(HttpServletRequest request, HttpServletResponse response){
//        response.setHeader("asdfasdfas", "asdfasdfasdfasdf");
        System.out.println("------------------------生成json并显示");
        User user = new User();
        user.setUsername("tianbaolei");
        user.setPassword("tianbaolei");

        Map<String,String> map = null;
        List<Map<String,String>> list = new ArrayList<Map<String, String>>();
        for(int i=0;i<5;i++){
            map = new HashMap<String, String>();
            map.put("key","value"+i);
            list.add(map);
        }
        return list;
    }
}
