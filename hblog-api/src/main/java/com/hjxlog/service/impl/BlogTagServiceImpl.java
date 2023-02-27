package com.hjxlog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.domain.BlogTag;
import com.hjxlog.service.BlogTagService;
import com.hjxlog.mapper.BlogTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjx
 * @description 针对表【blog_tag】的数据库操作Service实现
 * @createDate 2023-02-27 08:45:52
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag>
        implements BlogTagService {

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public List<Long> selectTagIdsByBlogId(Long blogId) {
        LambdaQueryWrapper<BlogTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogTag::getBlogId,blogId);
        List<BlogTag> blogTags = blogTagMapper.selectList(queryWrapper);
        List<Long> tagIds = blogTags.stream().map(BlogTag::getTagId).collect(Collectors.toList());
        return tagIds;
    }

}




