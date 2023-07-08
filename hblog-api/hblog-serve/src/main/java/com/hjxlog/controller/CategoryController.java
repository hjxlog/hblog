package com.hjxlog.controller;

import com.hjxlog.domain.Category;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.CategoryService;
import com.hjxlog.vo.CategoryVo;
import org.springframework.web.bind.annotation.GetMapping;
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
        return Result.success(list, CategoryVo.class);
    }

}
