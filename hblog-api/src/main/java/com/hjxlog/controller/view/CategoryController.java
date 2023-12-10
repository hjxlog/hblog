package com.hjxlog.controller.view;

import com.hjxlog.api.vo.CategoryServeVo;
import com.hjxlog.domain.Category;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.CategoryService;
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
