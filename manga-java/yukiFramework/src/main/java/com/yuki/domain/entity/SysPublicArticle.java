package com.yuki.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (SysPublicArticle)表实体类
 *
 * @author makejava
 * @since 2022-11-22 16:38:06
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_public_article")
public class SysPublicArticle  {
    @TableId
    private Integer id;

    
    private String content;
    
    private String title;
    
    private Integer userId;
    
    private Date created;
    
    private Integer status;



}

