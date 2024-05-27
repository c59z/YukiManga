package com.yuki.Controller;

import com.yuki.domain.ResponseResult;
import com.yuki.domain.dto.RegisterUser;
import com.yuki.domain.entity.SysUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController extends BaseController{

    @Transactional
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody SysUser user){
        return userService.userLogin(user);
    }

    @PostMapping("/user/register")
    public ResponseResult register(@RequestBody RegisterUser user){
        return userService.userRegister(user);
    }

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return userService.logout();
    }

    @PreAuthorize("hasAuthority('sys:test')")
    @GetMapping("/sys/test")
    public ResponseResult systest(){
        return ResponseResult.success("权限测试成功");
    }


}
