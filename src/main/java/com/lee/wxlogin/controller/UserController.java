package com.lee.wxlogin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.wxlogin.mapper.UserMapper;
import com.lee.wxlogin.model.User;
import com.lee.wxlogin.properties.WxProperties;
import com.lee.wxlogin.utils.JsonUtil;
import com.lee.wxlogin.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.PrimitiveIterator;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private WxProperties pro;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/getWxLoginUrl")
    @ResponseBody
    public Object getWxLoginUrl(@RequestParam("access_page") String access_page) throws Exception {
        String redirectUrl=pro.getRedictUrl();
        String encode = URLEncoder.encode(redirectUrl, "UTF-8");
        String loginFullUrl = String.format(pro.getLoginUrl(),
                pro.getAppId(), pro.getRedictUrl(), access_page);
        return loginFullUrl;
    }
    @RequestMapping("/back")
    public Object forBack(@RequestParam("code") String code,
                          @RequestParam("state") String state) throws IOException {
        User user=null;
        String accessTokenUrl=pro.getAccessTokenUrl();
        String fullAccessTokenUrl = String.format(accessTokenUrl, pro.getAppId(), pro.getAppSecret(), code);
        String backInfo = restTemplate.getForObject(fullAccessTokenUrl, String.class);
        Map map = JsonUtil.StringToMap(backInfo);
        String openId=(String) map.get("openid");
        String accessToken=(String)map.get("access_token");
        System.out.println(openId+":"+accessToken);
        //判断用户表中是否存在改openId
        user= userMapper.findUserByOpenId(openId);
        if(user==null) {
            String userInfoUrl = String.format(pro.getUserInfoUrl(), accessToken, openId);
            String userInfo = restTemplate.getForObject(userInfoUrl, String.class);
            Map userInfoMap = JsonUtil.StringToMap(userInfo);
            String nickName = (String) userInfoMap.get("nickname");
            Integer sex = (Integer) userInfoMap.get("sex");
            String headImg = (String) userInfoMap.get("headimgurl");
            user.setHeadImg(headImg);
            user.setNickName(new String(nickName.getBytes("ISO8859-1"),"UTF-8"));
            user.setSex(sex);
            user.setOpenId(openId);
            int flag= userMapper.saveUser(user);
            System.out.println("注册");
        }
        String jwt = JwtUtil.createJwt(user);
        System.out.println("登录");
        return "redirect:http://localhost/index.html?token="+jwt;
    }
    @RequestMapping("/add")
    @ResponseBody
    public Object add(){
        return "add";
    }
}
