package com.yuki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuki.domain.entity.SysMenuRole;
import com.yuki.mapper.SysMenuRoleMapper;
import com.yuki.service.SysMenuRoleService;
import org.springframework.stereotype.Service;

/**
 * (SysMenuRole)表服务实现类
 *
 * @author makejava
 * @since 2022-11-15 16:40:20
 */
@Service("sysMenuRoleService")
public class SysMenuRoleServiceImpl extends ServiceImpl<SysMenuRoleMapper, SysMenuRole> implements SysMenuRoleService {

}

