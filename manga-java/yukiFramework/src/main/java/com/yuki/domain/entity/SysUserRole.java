package com.yuki.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (SysUserRole)表实体类
 *
 * @author makejava
 * @since 2022-11-15 16:40:20
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_role")
public class SysUserRole  {
    @TableId
    private Long id;

    
    private Integer userId;
    
    private Integer roleId;



}

