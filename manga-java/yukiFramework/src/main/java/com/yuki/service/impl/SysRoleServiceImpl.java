package com.yuki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuki.domain.entity.SysRole;
import com.yuki.mapper.SysRoleMapper;
import com.yuki.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * (SysRole)表服务实现类
 *
 * @author makejava
 * @since 2022-11-15 16:40:20
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

}

