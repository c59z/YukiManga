package com.yuki.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInfoVo {
    private Long id;

    private Long manga_id;

    private String name;

    private String author;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime uploaddata;
    //评分,(1-5)分，1分最低，5分最高
    private Integer review;

    private String url;
    //上传者的id
    private Long uploadBy;

    private List<String> tag;

    private String cover;

    private Integer total;

    private Integer isadult;
}
