package com.hjxlog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.domain.BlogTag;
import com.hjxlog.mapper.BlogTagMapper;
import com.hjxlog.service.BlogTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hjx
 * @description 针对表【blog_tag】的数据库操作Service实现
 * @createDate 2023-06-30 23:33:41
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Resource
    private BlogTagMapper blogTagMapper;

    @Override
    public int deleteByBlogId(Integer blogId) {
        LambdaQueryWrapper<BlogTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogTag::getBlogId, blogId);
        return blogTagMapper.delete(queryWrapper);
    }
}




