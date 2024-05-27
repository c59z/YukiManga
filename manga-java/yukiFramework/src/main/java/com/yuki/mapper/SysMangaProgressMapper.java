package com.yuki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuki.domain.entity.SysBookinfo;
import com.yuki.domain.entity.SysMangaProgress;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (SysMangaProgress)表数据库访问层
 *
 * @author yuki
 * @since 2022-12-04 11:23:56
 */
public interface SysMangaProgressMapper extends BaseMapper<SysMangaProgress> {
    Integer getMangaProressRowId(@Param("userId") Long userId, @Param("mangaId") Long mangaId);

    Integer getMangaProressId(@Param("userId") Long userId,@Param("mangaId") Long mangaId);


    List<SysBookinfo> getMangaByProgress(@Param("userId") Long userId,@Param("progress") Long progress);

}

