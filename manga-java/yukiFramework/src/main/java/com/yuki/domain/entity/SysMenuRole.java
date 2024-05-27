package com.yuki.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (SysMenuRole)表实体类
 *
 * @author makejava
 * @since 2022-11-15 16:40:20
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu_role")
public class SysMenuRole  {
    @TableId
    private Long id;

    
    private Integer roleId;
    
    private Integer menuId;



}

