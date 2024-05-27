package com.yuki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuki.domain.ResponseResult;
import com.yuki.domain.dto.RegisterUser;
import com.yuki.domain.dto.UpdateUser;
import com.yuki.domain.entity.SysUser;

import java.util.List;


/**
 * (SysUser)表服务接口
 *
 * @author makejava
 * @since 2022-11-15 16:40:20
 */
public interface SysUserService extends IService<SysUser> {

    ResponseResult userLogin(SysUser user);

    ResponseResult logout();

    ResponseResult userRegister(RegisterUser user);

    SysUser GetUserByUsername(String username);

    ResponseResult getUserInfo();

    ResponseResult updateUserData(UpdateUser user);

    ResponseResult userAuthentication(String value);

    List<String> GetAllUserList(String curruser);

}

