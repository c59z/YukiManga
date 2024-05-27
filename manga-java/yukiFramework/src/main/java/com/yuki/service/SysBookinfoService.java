package com.yuki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuki.domain.ResponseResult;
import com.yuki.domain.dto.ReadingProgress;
import com.yuki.domain.entity.SysBookinfo;
import com.yuki.domain.dto.UploadFile;


/**
 * (SysBookinfo)表服务接口
 *
 * @author makejava
 * @since 2022-11-23 16:57:24
 */
public interface SysBookinfoService extends IService<SysBookinfo> {

    ResponseResult getBookList(Integer currentPage,Integer selectMethod,String selectWord);

    ResponseResult uploadFile(UploadFile uploadFile);

    ResponseResult getMangaDetail(Long id,Integer current);

}

