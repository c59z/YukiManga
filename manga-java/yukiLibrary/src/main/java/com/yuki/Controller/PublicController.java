package com.yuki.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuki.domain.ResponseResult;
import com.yuki.domain.entity.SysPublicArticle;
import com.yuki.service.SysPublicArticleService;
import com.yuki.service.SysUserService;
import com.yuki.utils.MarkDownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController extends BaseController{

    @Autowired
    SysPublicArticleService articleService;



    /**
     * 获取 关于 文章
     */
    @PostMapping("/about")
    public ResponseResult getAbout(){
        QueryWrapper<SysPublicArticle> queryWrapper = new QueryWrapper<>();
        SysPublicArticle about = articleService.getOne(queryWrapper.eq("id", 1));
        String content = about.getContent();
        String content_html = MarkDownUtils.MarkDownToHtml(content);
        about.setContent(content_html);
        return ResponseResult.success(200,"about",about);
    }


}
