package com.hjxlog.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjxlog.api.dto.TagDto;
import com.hjxlog.domain.Tag;
import com.hjxlog.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@SpringBootTest
public class TagServiceTests {

    @Resource
    private TagService tagService;

    @Test
    public void testGetList() {
        TagDto dto = new TagDto();
        Page<Tag> tagPage = tagService.getList(dto);
        List<Tag> list = tagPage.getRecords();
        System.out.println("list = " + list);
    }

    @Test
    public void testAddTag() {
        TagDto dto = new TagDto();
        dto.setName("添加标签测试名称" + new Date());
        Tag Tag = tagService.addTag(dto);
        System.out.println("Tag = " + Tag);
    }

    @Test
    public void testUpdateTag() {
        TagDto dto = new TagDto();
        dto.setName("添加标签测试名称" + new Date());
        Tag Tag = tagService.addTag(dto);
        System.out.println("Tag = " + Tag);
        dto.setId(Tag.getId());
        dto.setName("更新标签测试名称" + new Date());
        Tag updateTag = tagService.updateTag(dto);
        System.out.println("updateTag = " + updateTag);
    }

    @Test
    public void testDeleteTag() {
        TagDto dto = new TagDto();
        dto.setName("标签测试名称");
        Page<Tag> list = tagService.getList(dto);
        List<Tag> records = list.getRecords();
        if (CollectionUtil.isNotEmpty(records)) {
            int i = tagService.deleteTag(records.get(0).getId());
            System.out.println("i = " + i);
        }
    }

}
