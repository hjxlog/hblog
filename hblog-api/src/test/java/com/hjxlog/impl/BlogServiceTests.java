package com.hjxlog.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjxlog.api.dto.BlogQueryDto;
import com.hjxlog.api.dto.BlogSaveDto;
import com.hjxlog.api.dto.CategoryDto;
import com.hjxlog.api.dto.TagDto;
import com.hjxlog.api.vo.BlogVo;
import com.hjxlog.domain.Category;
import com.hjxlog.domain.Tag;
import com.hjxlog.service.BlogService;
import com.hjxlog.service.CategoryService;
import com.hjxlog.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@SpringBootTest
public class BlogServiceTests {

    @Resource
    private BlogService blogService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private TagService tagService;

    @Test
    public void testGetListAndDetail() {
        BlogQueryDto blogQueryDto = new BlogQueryDto();
        Page<BlogVo> blogVoPage = blogService.getList(blogQueryDto);
        List<BlogVo> records = blogVoPage.getRecords();
        System.out.println("博客列表 = " + records);
        if (CollectionUtil.isNotEmpty(records)) {
            Integer id = records.get(0).getId();
            BlogQueryDto dto = new BlogQueryDto();
            dto.setId(id);
            BlogVo blogVo = blogService.getDetail(dto.getId());
            System.out.println("博客详情 = " + blogVo);
        }
    }

    @Test
    public void testAddAndUpdateBlog() {
        // 添加分类
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("添加分类测试名称" + new Date());
        Category category = categoryService.addCategory(categoryDto);
        // 添加标签
        TagDto tagDto = new TagDto();
        tagDto.setName("添加标签测试名称" + new Date());
        Tag tag = tagService.addTag(tagDto);
        // 添加博客
        BlogSaveDto blogSaveDto = new BlogSaveDto();
        blogSaveDto.setTitle("添加博客测试-标题");
        blogSaveDto.setCategoryId(category.getId());
        blogSaveDto.setTagIds(Arrays.asList(tag.getId()));
        BlogVo blogVo = blogService.addBlog(blogSaveDto);
        System.out.println("添加博客数据 = " + blogVo);
        // 更新博客
        blogSaveDto.setId(blogVo.getId());
        blogSaveDto.setTitle("更新博客测试-标题");
        BlogVo updateBlogVo = blogService.updateBlog(blogSaveDto);
        System.out.println("更新博客数据 = " + updateBlogVo);
    }

    @Test
    public void testDeleteBlog() {
        int i = blogService.deleteBlog(15);
        System.out.println("变更条数 = " + i);
    }

}
