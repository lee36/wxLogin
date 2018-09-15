package com.lee.wxlogin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.wxlogin.exception.NoTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    public Object message(Exception e){
        if(e instanceof NoTokenException){
            NoTokenException noTokenException=(NoTokenException)e;
            return noTokenException.getMessage();
        }
        return null;
    }
}
