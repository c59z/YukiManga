package com.yuki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuki.domain.ResponseResult;
import com.yuki.domain.dto.ReadingProgress;
import com.yuki.domain.entity.SysMangaProgress;


/**
 * (SysMangaProgress)表服务接口
 *
 * @author yuki
 * @since 2022-12-04 11:23:57
 */
public interface SysMangaProgressService extends IService<SysMangaProgress> {

    ResponseResult updateProgress(SysMangaProgress progress);

    Long getProgress(Long mangaId);

    ResponseResult getMangaListByProgress(Long progress);
}

