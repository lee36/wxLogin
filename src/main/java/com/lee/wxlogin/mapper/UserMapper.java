package com.lee.wxlogin.mapper;

import com.lee.wxlogin.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface UserMapper {
    //通过openId获取用户
    @Select("select * from user where open_id=#{openId}")
    public User findUserByOpenId(String openId);
    @Insert("Insert into user" +
            "(sex,head_img,nick_name,open_id) values(#{sex}" +
            ",#{headImg},#{nickName},#{openId})")
    @Options(keyProperty = "id",useGeneratedKeys = true)
    public int saveUser(User user);
}
