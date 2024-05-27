package com.yuki.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuki.domain.Const.SysConst;
import com.yuki.domain.ResponseResult;
import com.yuki.domain.dto.ReadingProgress;
import com.yuki.domain.entity.SysBookinfo;
import com.yuki.domain.dto.UploadFile;
import com.yuki.domain.entity.SysMangaProgress;
import com.yuki.domain.vo.BookDetailVo;
import com.yuki.domain.vo.BookInfoVo;
import com.yuki.mapper.SysBookinfoMapper;
import com.yuki.mapper.SysMangaProgressMapper;
import com.yuki.security.LoginUser;
import com.yuki.service.SysBookinfoService;
import com.yuki.service.SysMangaProgressService;
import com.yuki.utils.BookUtils;
import com.yuki.utils.MultipartFileToFile;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * (SysBookinfo)表服务实现类
 *
 * @author makejava
 * @since 2022-11-23 16:57:25
 */
@Service("sysBookinfoService")
public class SysBookinfoServiceImpl extends ServiceImpl<SysBookinfoMapper, SysBookinfo> implements SysBookinfoService {

    @Autowired
    SysBookinfoMapper bookinfoMapper;

    @Autowired
    SysMangaProgressService mangaProgressService;



    @Override
    public ResponseResult getBookList(Integer current,Integer selectMethod,String selectWord) {

        System.out.println("===========================");
        System.out.println(current);
        System.out.println("===========================");

        Page c_page = new Page(current,12);

        QueryWrapper<SysBookinfo> queryWrapper = new QueryWrapper();

        if (selectMethod == 1){
            // 默认降序
            queryWrapper.orderByDesc("uploadData");
        }else if (selectMethod == 2){
            queryWrapper.orderByAsc("uploadData");
        }else{
            return ResponseResult.fail(400,"未知错误:400");
        }

        // 获取有关漫画的所有个数
        Integer bookCount = null;
        // 如果传过来的数据存在，并且传过来的数据不是undefined
        if (StringUtils.hasText(selectWord) && !selectWord.equals("undefined") && !selectWord.equals("null")){
            // 如果传过来的参数存在
            queryWrapper.like("name",selectWord).or().like("author",selectWord).or().like("tags",selectWord);
            StringBuilder sb = new StringBuilder(selectWord);
            sb.insert(0,'%');
            sb.insert(selectWord.length(),'%');
            bookCount = bookinfoMapper.getBookCountBySelectWord(selectWord);
        }else{
            // 如果传过来的参数什么都没有,则什么都不干
            bookCount = bookinfoMapper.getBookCount();
        }
        System.out.println(selectWord);


        IPage iPage =this.page(c_page,queryWrapper);
        // 使用拿到的分页(iPage)数据,获取每一本书的封面(其实就是漫画的第1页)
        List<SysBookinfo> records = iPage.getRecords();
        List<BookInfoVo> bookInfoVos = new ArrayList<>();

        for (SysBookinfo book : records) {
            String name = book.getName();
            String cover = BookUtils.GetBookCoverByName(name);
            BookInfoVo bookInfoVo = new BookInfoVo();

            // 设置Vo类
            bookInfoVo.setName(book.getName());
            bookInfoVo.setId(book.getId());
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
            // 添加Vo
            bookInfoVos.add(bookInfoVo);
        }
        return ResponseResult.success(200,"获取图书信息成功!", MapUtil.builder()
                .put("books",bookInfoVos)
                .put("total",bookCount)
                .map());
    }

    @Override
    public ResponseResult uploadFile(UploadFile uploadFile) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();

        MultipartFile file = uploadFile.getFile();
        String fileName = uploadFile.getFileName();



        String savePath = SysConst.SYS_PATH+fileName;
        // 将上传的图书存到本地
        String s = MultipartFileToFile.saveMultipartFile(file, savePath, fileName);
        if (Objects.isNull(s)){
            return ResponseResult.fail("上传失败");
        }

        SysBookinfo bookinfo = new SysBookinfo();
        bookinfo.setAuthor(uploadFile.getAuthor());
        bookinfo.setName(uploadFile.getFileName());
        bookinfo.setUploaddata(LocalDateTime.now());
        /* 假设传过来的tags是 "全彩,CG,纯爱," */
        bookinfo.setTags(uploadFile.getTags());
        bookinfo.setIsadult(uploadFile.getIsadult());
        bookinfo.setUploadBy(userId);
        bookinfo.setUrl(s);

        boolean save = this.save(bookinfo);

        if (save){
            return ResponseResult.success(200,"上传成功!",s);
        }else{
            return ResponseResult.success(400,"上传失败!",s);
        }

    }

    @Override
    public ResponseResult getMangaDetail(Long id,Integer current) {

        SysBookinfo book = this.getById(id);

        if (Objects.isNull(book)){
            return ResponseResult.fail("该图书不存在");
        }

        String tags = book.getTags();
        String[] tags_s = tags.split(",");
        System.out.println(tags_s);

        List<String> strings_tags = Arrays.asList(tags_s);
        String cover = BookUtils.GetBookCoverByName(book.getName());

        BookDetailVo bookVo = new BookDetailVo();
        bookVo.setName(book.getName());
        bookVo.setTag(strings_tags);
        bookVo.setIsadult(book.getIsadult());
        bookVo.setAuthor(book.getAuthor());
        bookVo.setId(book.getId());
        bookVo.setUrl(book.getUrl());
        bookVo.setCover(cover);
        bookVo.setUploaddata(book.getUploaddata());
        bookVo.setUploadBy(book.getUploadBy());
        List<String> content = BookUtils.GetBookDetailByName(bookVo.getUrl(),current);
        bookVo.setContent(content);

        Integer count = BookUtils.GetBookCount(book.getName());

//        return ResponseResult.success(200,"获取图书信息成功!", bookVo);
        // 获取用户阅读漫画的进度
        Long progress = mangaProgressService.getProgress(book.getId());

        return ResponseResult.success(200,"获取图书信息成功!", MapUtil.builder()
                .put("book",bookVo)
                .put("total",count)
                .put("progress",progress)
                .map());
    }


}

