package com.lee.wxlogin.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private int sex;
    private String headImg;
    private String nickName;
    private String openId;
}
