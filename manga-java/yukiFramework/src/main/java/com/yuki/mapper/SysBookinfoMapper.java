package com.yuki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuki.domain.entity.SysBookinfo;


/**
 * (SysBookinfo)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-23 16:57:22
 */
public interface SysBookinfoMapper extends BaseMapper<SysBookinfo> {
    Integer getBookCount();

    Integer getBookCountBySelectWord(String selectWord);
}

