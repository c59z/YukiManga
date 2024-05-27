package com.yuki.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailVo {
    // 漫画id
    private Long id;
    // 漫画名
    private String name;
    // 漫画作者
    private String author;
    // 上传该漫画的日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime uploaddata;
    // 评分,(1-5)分，1分最低，5分最高
    private Integer review;
    // 存在本地服务器的漫画
    private String url;
    // 上传者的id
    private Long uploadBy;
    // 漫画封面
    private String cover;

    private List<String> content;

    // 漫画tag
    private List<String> tag;
    // 分级 true:R18 false:全年龄
    private Integer isadult;
}
