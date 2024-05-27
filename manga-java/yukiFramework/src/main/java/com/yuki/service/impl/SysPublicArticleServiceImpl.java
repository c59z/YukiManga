package com.yuki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuki.domain.entity.SysPublicArticle;
import com.yuki.mapper.SysPublicArticleMapper;
import com.yuki.service.SysPublicArticleService;
import org.springframework.stereotype.Service;

/**
 * (SysPublicArticle)表服务实现类
 *
 * @author makejava
 * @since 2022-11-22 16:38:07
 */
@Service("sysPublicArticleService")
public class SysPublicArticleServiceImpl extends ServiceImpl<SysPublicArticleMapper, SysPublicArticle> implements SysPublicArticleService {

}

