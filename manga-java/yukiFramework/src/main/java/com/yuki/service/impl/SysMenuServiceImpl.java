package com.yuki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuki.domain.entity.SysMenu;
import com.yuki.service.SysMenuService;
import com.yuki.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;

/**
 * (SysMenu)表服务实现类
 *
 * @author makejava
 * @since 2022-11-15 16:40:20
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

}

