package com.lee.wxlogin;

import com.lee.wxlogin.mapper.UserMapper;
import com.lee.wxlogin.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxloginApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        User user=new User();
        user.setOpenId("12");
        user.setSex(1);
        user.setHeadImg("1");
        user.setNickName("ha");
        userMapper.saveUser(user);
        System.out.println(user);
    }

}
