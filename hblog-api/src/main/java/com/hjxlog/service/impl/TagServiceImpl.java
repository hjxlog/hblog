package com.hjxlog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.domain.Tag;
import com.hjxlog.service.BlogTagService;
import com.hjxlog.service.TagService;
import com.hjxlog.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hjx
 * @description 针对表【tag】的数据库操作Service实现
 * @createDate 2023-02-27 08:45:52
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
        implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private BlogTagService blogTagService;

    @Override
    public List<Tag> selectByBlogId(Long blogId) {
        List<Tag> tags = new ArrayList<>();
        List<Long> tagIds = blogTagService.selectTagIdsByBlogId(blogId);
        if (CollectionUtils.isNotEmpty(tagIds)) {
            tags = tagMapper.selectBatchIds(tagIds);
        }
        return tags;
    }
}




