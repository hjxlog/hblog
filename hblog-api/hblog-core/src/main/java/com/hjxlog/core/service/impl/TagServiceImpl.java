package com.hjxlog.core.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.core.api.dto.TagDto;
import com.hjxlog.core.domain.Blog;
import com.hjxlog.core.domain.BlogTag;
import com.hjxlog.core.domain.Tag;
import com.hjxlog.core.exception.SystemException;
import com.hjxlog.core.mapper.TagMapper;
import com.hjxlog.core.service.BlogService;
import com.hjxlog.core.service.BlogTagService;
import com.hjxlog.core.service.TagService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjx
 * @description 针对表【tag】的数据库操作Service实现
 * @createDate 2023-06-30 23:33:41
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Lazy
    @Resource
    private BlogService blogService;

    @Resource
    private BlogTagService blogTagService;

    @Override
    public Page<Tag> getList(TagDto dto) {
        Page<Tag> page = Page.of(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(dto.getName()), Tag::getName, dto.getName());
        queryWrapper.orderByDesc(Tag::getUpdateTime);
        Page<Tag> tagPage = tagMapper.selectPage(page, queryWrapper);
        return tagPage;
    }

    @Override
    public Tag addTag(TagDto dto) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Tag::getName, dto.getName());
        Tag tag = tagMapper.selectOne(queryWrapper);
        if (tag != null) {
            throw new SystemException("该标签已存在。");
        }
        Tag addTag = new Tag();
        BeanUtil.copyProperties(dto, addTag);
        tagMapper.insert(addTag);
        return addTag;
    }

    @Override
    public Tag updateTag(TagDto dto) {
        Tag updateTag = tagMapper.selectById(dto.getId());
        if (updateTag == null) {
            throw new SystemException("该标签不存在。");
        }
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Tag::getName, dto.getName());
        Tag selectOne = tagMapper.selectOne(queryWrapper);
        if (selectOne != null && !selectOne.getId().equals(dto.getId())) {
            throw new SystemException("该标签已存在。");
        }
        BeanUtil.copyProperties(dto, updateTag);
        tagMapper.updateById(updateTag);
        return updateTag;
    }

    @Override
    public int deleteTag(Integer id) {
        if (id == null) {
            throw new SystemException("标签id为空。");
        }
        return tagMapper.deleteById(id);
    }

    @Override
    public List<Tag> selectPublishedTag() {
        // 已发布的博客ids
        List<Integer> publishedBlogIds = blogService.selectColumnsByPublished(Blog::getId)
                .stream()
                .map(Blog::getId)
                .collect(Collectors.toList());
        List<Integer> publishedTagIds = blogTagService.selectByBlogIds(publishedBlogIds)
                .stream()
                .map(BlogTag::getTagId)
                .distinct()
                .collect(Collectors.toList());
        List<Tag> tags = listByIds(publishedTagIds);
        return tags;
    }
}




