package com.yuki.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadingProgress {
    // 用户id
    private Long userId;
    // 漫画id
    private Long mangaId;
    // 进度
    /**
     * 想看:marked / 1
     * 在看:reading / 2
     * 看过:read / 3
     * 重看:replay / 4
     * 搁置:postponed / 5
     */
    private Long progress;
}
