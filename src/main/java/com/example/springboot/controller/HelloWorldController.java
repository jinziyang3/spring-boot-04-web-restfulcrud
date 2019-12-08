package com.example.springboot.controller;

import com.example.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @author JinZiyang
 * @create 2019-11-28 - 16:29
 */
@Controller
public class HelloWorldController {
//    @RequestMapping({"/","/index.html"})
//    public String index(){
//        return"index";
//    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam("user")String user){
        if (user.equals("aaa")){
            throw new UserNotExistException();
        }
        return"HelloWorld";
    }

    //查出一些数据在页面展示

    @RequestMapping("/seccess")
    public String success(Map<String,Object> map){
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("张三","李四","王五"));
        //"classpath:/templates/seccess.html"
        return "list";
    }


}
