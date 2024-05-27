package com.yuki.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yuki.domain.entity.SysUser;
import com.yuki.mapper.SysMenuMapper;
import com.yuki.mapper.SysUserMapper;
import com.yuki.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    SysMenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

//        System.out.println("啦啦啦啦啦啦啦啦啦啦啦啦啦");

        // 查询用户信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername,s);
        SysUser user = userMapper.selectOne(queryWrapper);

        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }

        // TODO 查询对应的权限信息

        List<String> perms = menuMapper.selectMenuByUserId(user.getId());
//        System.out.println("该用户拥有的权限 :"+perms.toString());
//        List<String> perms = new ArrayList<>(Arrays.asList("sys:test"));
        return new LoginUser(user,perms);
    }
}
