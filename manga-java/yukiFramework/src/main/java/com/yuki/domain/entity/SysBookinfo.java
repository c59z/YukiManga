package com.yuki.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (SysBookinfo)表实体类
 *
 * @author makejava
 * @since 2022-11-23 16:57:24
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_bookinfo")
public class SysBookinfo  {
    @TableId
    private Long id;

    @TableField(value="mangaId",select = false)
    private Long mangaId;

    private String name;
    
    private String author;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime uploaddata;
    //评分,(1-5)分，1分最低，5分最高
    private Integer review;
    
    private String url;
    //上传者的id
    private Long uploadBy;

    // 漫画tag
    private String tags;
    // 分级 true:R18 false:全年龄
    private Integer isadult;
}

