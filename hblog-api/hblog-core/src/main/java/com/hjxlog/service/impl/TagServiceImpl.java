package com.hjxlog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.api.dto.TagDto;
import com.hjxlog.domain.Tag;
import com.hjxlog.exception.SystemException;
import com.hjxlog.mapper.TagMapper;
import com.hjxlog.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hjx
 * @description 针对表【tag】的数据库操作Service实现
 * @createDate 2023-06-30 23:33:41
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Resource
    private TagMapper tagMapper;

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
}




