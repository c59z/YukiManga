package com.yuki;

import com.yuki.mapper.SysMangaProgressMapper;
import com.yuki.mapper.SysMenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class TestController {
//
//    @Autowired
//    SysMenuMapper menuMapper;


    @Test
    public void passedcoder(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123qwe");
        System.out.println(encode);
    }




//    @Test
//    public void testSelectMenuByUserId(){
//        List<String> list = menuMapper.selectMenuByUserId(1L);
//        System.out.println(list);
//    }

}
