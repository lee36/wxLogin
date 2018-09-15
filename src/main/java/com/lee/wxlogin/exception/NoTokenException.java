package com.lee.wxlogin.exception;

public class NoTokenException extends Exception{

    private String message;

    public NoTokenException(String message){
        super(message);
    }
}
