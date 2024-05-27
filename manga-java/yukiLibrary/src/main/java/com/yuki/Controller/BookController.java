package com.yuki.Controller;

import com.fasterxml.jackson.core.util.RequestPayload;
import com.yuki.domain.Const.BookConst;
import com.yuki.domain.ResponseResult;
import com.yuki.domain.dto.ReadingProgress;
import com.yuki.domain.dto.UploadFile;
import com.yuki.domain.entity.SysMangaProgress;
import com.yuki.mapper.SysMangaProgressMapper;
import com.yuki.service.SysBookinfoService;
import com.yuki.service.SysMangaProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController extends BaseController {

    @Autowired
    SysBookinfoService bookinfoService;

    @Autowired
    SysMangaProgressService mangaProgressService;

    /**
     * 测试本地获取图片
     * @return
     */
    @PreAuthorize("hasAuthority('sys:test')")
    @PostMapping("/testgetbook")
    public ResponseResult testGetBook(){
        List<String> photo_list = new ArrayList<>();
        String defUrl = "D:/code/Project/java/前后端分离-漫画系统/libraryServer/";
        String bookName = "anmi";
        File file_dir = new File(defUrl+bookName);
        File[] files = file_dir.listFiles();
        for (File file : files) {
            String name = file.getName();
            // 拼接字符串
            photo_list.add(BookConst.WEB_PATH+bookName+"/"+file.getName());
        }
        return ResponseResult.success(photo_list);
    }


    /**
     * 获取图书列表
     * @param currentPage
     * @return
     */
    @GetMapping("/getBookList")
    public ResponseResult getBookList(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "1") Integer selectMethod,@RequestParam(defaultValue = "") String selectWord){
        return bookinfoService.getBookList(currentPage,selectMethod,selectWord);
    }

    /**
     * 上传漫画
     * @param uploadFile
     * @return
     */
    @Transactional
    @PostMapping("/upload")
    public ResponseResult uploadBook(UploadFile uploadFile){
        return bookinfoService.uploadFile(uploadFile);
    }

    /**
     * 漫画详情
     * @param id
     * @return
     */
//    @Transactional
    @GetMapping("/detail/{id}/{currentPage}")
    public ResponseResult getBookDetail(@PathVariable("id") Long id,@PathVariable("currentPage") Integer currentPage){
        System.out.println(currentPage);
        return bookinfoService.getMangaDetail(id,currentPage);
    }

    /**
     * 更新进度
     * @param progress
     * @return
     */
//    @Transactional
    @PutMapping("/updateProgress")
    public ResponseResult updateProgress(@RequestBody SysMangaProgress progress){
        return mangaProgressService.updateProgress(progress);
    }

    /**
     * BUG 读取的漫画id不一样
     */
    // 通过进度获取漫画
    @GetMapping("/favorite/getMangaByProgress/{progress}")
    public ResponseResult getMangaByProgress(@PathVariable("progress") Long progress){
        System.out.println(progress);
        return mangaProgressService.getMangaListByProgress(progress);
    }

}
