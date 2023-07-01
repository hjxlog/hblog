package com.hjxlog.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjxlog.api.dto.CategoryDto;
import com.hjxlog.domain.Category;
import com.hjxlog.service.CategoryService;
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
public class CategoryServiceTests {

    @Resource
    private CategoryService categoryService;

    @Test
    public void testGetList() {
        CategoryDto dto = new CategoryDto();
        Page<Category> categoryPage = categoryService.getList(dto);
        List<Category> list = categoryPage.getRecords();
        System.out.println("list = " + list);
    }

    @Test
    public void testAddCategory() {
        CategoryDto dto = new CategoryDto();
        dto.setName("添加分类测试名称" + new Date());
        Category category = categoryService.addCategory(dto);
        System.out.println("category = " + category);
    }

    @Test
    public void testUpdateCategory() {
        CategoryDto dto = new CategoryDto();
        dto.setName("添加分类测试名称" + new Date());
        Category category = categoryService.addCategory(dto);
        System.out.println("category = " + category);
        dto.setId(category.getId());
        dto.setName("更新分类测试名称" + new Date());
        Category updateCategory = categoryService.updateCategory(dto);
        System.out.println("updateCategory = " + updateCategory);
    }

    @Test
    public void testDeleteCategory() {
        CategoryDto dto = new CategoryDto();
        dto.setName("分类测试名称");
        Page<Category> list = categoryService.getList(dto);
        for (Category record : list.getRecords()) {
            CategoryDto deleteDto = new CategoryDto();
            deleteDto.setId(record.getId());
            int i = categoryService.deleteCategory(deleteDto);
            System.out.println("i = " + i);
        }
    }

}
