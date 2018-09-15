package com.lee.wxlogin.utils;

import com.lee.wxlogin.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private static final String SUBJECT="lee";
    private static final Long EXPRETIME=24*60*60*1000L;
    private static final String APPID="lhj666";

    public static String createJwt(User user){
        String s = Jwts.builder().setSubject(SUBJECT).
                setExpiration(new Date(EXPRETIME + System.currentTimeMillis()))
                .claim("nickName", user.getNickName()).
                        claim("headImg", user.getHeadImg()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512,APPID).compact();
        return s;
    }

    public static Claims parseJwt(String str){
        Claims body = Jwts.parser().setSigningKey(APPID).parseClaimsJws(str).getBody();
        return body;
    }
}
