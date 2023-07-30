package com.hjxlog.core.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.core.domain.BlogTag;
import com.hjxlog.core.mapper.BlogTagMapper;
import com.hjxlog.core.service.BlogTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<BlogTag> selectByBlogIds(List<Integer> blogIds) {
        List<BlogTag> result = new ArrayList<>();
        if (CollectionUtil.isEmpty(blogIds)) {
            return result;
        }
        LambdaQueryWrapper<BlogTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(BlogTag::getBlogId, blogIds);
        result = list(queryWrapper);
        return result;
    }
}




