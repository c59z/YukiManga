package com.yuki.Controller;

import com.yuki.mapper.SysUserMapper;
import com.yuki.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @Autowired
    SysUserService userService;

    @Autowired
    SysUserMapper userMapper;

}
