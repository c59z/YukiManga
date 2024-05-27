package com.yuki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuki.domain.ResponseResult;
import com.yuki.domain.entity.SysBookinfo;
import com.yuki.domain.entity.SysMangaProgress;
import com.yuki.domain.vo.BookInfoVo;
import com.yuki.mapper.SysMangaProgressMapper;
import com.yuki.security.LoginUser;
import com.yuki.service.SysMangaProgressService;
import com.yuki.utils.BookUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * (SysMangaProgress)表服务实现类
 *
 * @author yuki
 * @since 2022-12-04 11:23:57
 */
@Service("sysMangaProgressService")
public class SysMangaProgressServiceImpl extends ServiceImpl<SysMangaProgressMapper, SysMangaProgress> implements SysMangaProgressService {

    @Autowired
    SysMangaProgressMapper mangaProgressMapper;

    @Override
    public ResponseResult updateProgress(SysMangaProgress progress) {
        // 先查询数据库中有没有用户id和漫画id对应的数据
        // 用户id
//        Long userId = progress.getUserId();
        // userid不需要从前端传过来
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        progress.setUserId(userId);
        // 漫画id
        Long mangaId = progress.getMangaId();
        // 先查询user_id 和 manga_id 对应的数据是否存在,如果存在使用update
        // 如果不存在使用insert
        // 下面的值,如果没有找到返回的值是null
        Integer rowid = mangaProgressMapper.getMangaProressRowId(userId, mangaId);

        if (Objects.isNull(rowid)){
            // 如果没有查询到数据 insert
            int id = mangaProgressMapper.insert(progress);
        }else{
            // 如果查到了数据就修改数据 update
            progress.setId(rowid);
            int i = mangaProgressMapper.updateById(progress);
        }
        System.out.println(">>>>>>>>>>>>>>"+rowid);
        return ResponseResult.success(200,"更新成功",progress);
    }

    @Override
    public Long getProgress(Long mangaId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();

        Integer mangaProressId = mangaProgressMapper.getMangaProressId(userId, mangaId);
        if (Objects.isNull(mangaProressId)){
            return 0L;
        }

        return Long.valueOf(mangaProressId);
    }

    @Override
    public ResponseResult getMangaListByProgress(Long progress) {
        // 根据 progress 获取这个user中对应的所有该进度的漫画
        // 获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        // 获取同一个userId下所有的该进度的漫画
        List<BookInfoVo> bookInfoVos = new ArrayList<>();
        List<SysBookinfo> mangaInfo = mangaProgressMapper.getMangaByProgress(userId,progress);

        System.out.println("==================================");
        for (SysBookinfo sysBookinfo : mangaInfo) {
            System.out.println(sysBookinfo);
        }
        System.out.println("==================================");

        for (SysBookinfo book : mangaInfo) {
            String name = book.getName();
            String cover = BookUtils.GetBookCoverByName(name);
            BookInfoVo bookInfoVo = new BookInfoVo();
            bookInfoVo.setName(book.getName());
            bookInfoVo.setId(book.getId());
            bookInfoVo.setManga_id(book.getMangaId());
            bookInfoVo.setAuthor(book.getAuthor());
            bookInfoVo.setReview(book.getReview());
            bookInfoVo.setUploaddata(book.getUploaddata());
            bookInfoVo.setUrl(book.getUrl());
            bookInfoVo.setCover(cover);
            bookInfoVo.setIsadult(book.getIsadult());
            String tags = book.getTags();
            String[] tags_s = tags.split(",");
            System.out.println(tags_s);

            List<String> strings_tags = Arrays.asList(tags_s);
            bookInfoVo.setTag(strings_tags);
            bookInfoVos.add(bookInfoVo);
        }
        return ResponseResult.success(bookInfoVos);
    }

}

