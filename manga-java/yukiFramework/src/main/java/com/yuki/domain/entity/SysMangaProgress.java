package com.yuki.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (SysMangaProgress)表实体类
 *
 * @author yuki
 * @since 2022-12-04 11:23:57
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_manga_progress")
public class SysMangaProgress  {
    @TableId
    private Integer id;

    // 用户id
    private Long userId;
    // 漫画id
    private Long mangaId;

    // 进度
    /**
     * 想看:marked
     * 在看:reading
     * 看过:read
     * 重看:replay
     * 搁置:postponed
     */
    private String progress;



}

