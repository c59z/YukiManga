package com.yuki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuki.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * (SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-15 16:40:20
 */
public interface SysUserMapper extends BaseMapper<SysUser> {


    String selectRoleNameById(Long id);
    List<String> selectAllUserName(String curruser);
}

