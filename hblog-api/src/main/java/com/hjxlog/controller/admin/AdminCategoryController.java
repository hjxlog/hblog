package com.hjxlog.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjxlog.annotation.AppLogger;
import com.hjxlog.api.dto.CategoryDto;
import com.hjxlog.domain.Category;
import com.hjxlog.protocol.PageResult;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * admin 分类接口
 *
 * @author: Huang JX
 * @date: 2023/7/1
 */
@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/list")
    public Result getList(@RequestBody CategoryDto dto) {
        Page<Category> list = categoryService.getList(dto);
        return Result.success(new PageResult<>(list, Category.class));
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Integer id) {
        Category category = categoryService.getById(id);
        return Result.success(category);
    }

    @AppLogger
    @PostMapping("/add")
    public Result addCategory(@Valid @RequestBody CategoryDto dto) {
        Category category = categoryService.addCategory(dto);
        return Result.success(category);
    }

    @AppLogger
    @PostMapping("/update")
    public Result updateCategory(@Valid @RequestBody CategoryDto dto) {
        Category category = categoryService.updateCategory(dto);
        return Result.success(category);
    }

    @AppLogger
    @PostMapping("/delete/{id}")
    public Result deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }

}
