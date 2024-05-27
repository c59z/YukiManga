package com.yuki.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuki.domain.ResponseResult;
import com.yuki.domain.entity.SysUser;
import com.yuki.mapper.SysMenuMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController extends BaseController {


//    @Autowired
//    PasswordEncoder passwordEncoder;




    @GetMapping("/test")
    public ResponseResult test(){
        return ResponseResult.success("本子昊牛逼");
    }

    @GetMapping("/list")
    public SysUser user(){
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername,"yuki");
        return userMapper.selectOne(queryWrapper);
    }

//    @GetMapping("/testCors")
//    public ResponseResult testCors(){
//        return ResponseResult.success(200,"跨域测试成功!");
//    }

//    @Test
//    public void testSelectMenuByUserId(){
//        List<String> list = menuMapper.selectMenuByUserId(1L);
//        System.out.println(list);
//    }






}
