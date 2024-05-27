package com.yuki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuki.domain.entity.SysMenu;

import java.util.List;


/**
 * (SysMenu)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-15 16:40:18
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<String> selectMenuByUserId(Long id);
}

