package com.yuki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuki.domain.entity.SysUserRole;
import com.yuki.mapper.SysUserRoleMapper;
import com.yuki.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * (SysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2022-11-15 16:40:20
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}

