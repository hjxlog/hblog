package com.hjxlog.serve.controller;

import com.hjxlog.core.domain.Category;
import com.hjxlog.core.protocol.Result;
import com.hjxlog.core.service.CategoryService;
import com.hjxlog.serve.vo.CategoryServeVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Huang JX
 * @date: 2023/7/8
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public Result getList() {
        List<Category> list = categoryService.selectPublishedCategory();
        return Result.success(list, CategoryServeVo.class);
    }

    @GetMapping("/getDetail/{id}")
    public Result getDetail(@PathVariable Integer id) {
        List<Category> list = categoryService.selectPublishedCategory();
        Category category = list.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .get();
        return Result.success(category, CategoryServeVo.class);
    }

}
