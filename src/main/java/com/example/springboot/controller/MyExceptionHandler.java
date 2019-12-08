package com.example.springboot.controller;

import com.example.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jinziyang
 * @date 2019-12-04-16:29
 * @category
 */
@ControllerAdvice
public class MyExceptionHandler {
    //浏览器，客户端返回的都是JSON
   /* @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String,Object> handlerException(Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notexist");
        map.put("massage",e.getMessage());

       return  map;
    }*/

    @ExceptionHandler(UserNotExistException.class)
    public String handlerException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //传入我们自己的错误状态码
      /*Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");*/
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist");
        map.put("massage","用户出错了");
        request.setAttribute("ext",map);
        //转发到/error
        return  "forward:/error";
    }
}
