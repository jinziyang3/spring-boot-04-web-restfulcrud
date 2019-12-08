package com.example.springboot.exception;

/**
 * @author jinziyang
 * @date 2019-12-04-16:21
 * @category
 */
public class UserNotExistException extends RuntimeException{
    public UserNotExistException() {
        super("用户不存在");
    }
}
