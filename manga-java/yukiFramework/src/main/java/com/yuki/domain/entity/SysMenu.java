package com.yuki.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (SysMenu)表实体类
 *
 * @author makejava
 * @since 2022-11-15 16:40:19
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
public class SysMenu  {
    @TableId
    private Integer id;

    
    private String menuName;
    
    private String perm;



}

