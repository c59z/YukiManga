package com.yuki.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (SysRole)表实体类
 *
 * @author makejava
 * @since 2022-11-15 16:40:20
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role")
public class SysRole  {
    @TableId
    private Long id;

    
    private String name;
    //角色的标识符
    private String code;
    //说明该身份可以做什么事情
    private String remark;
    
    private Date createdate;
    
    private Date updated;
    
    private Integer state;



}

