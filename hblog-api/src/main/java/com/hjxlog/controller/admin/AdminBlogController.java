package com.hjxlog.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjxlog.annotation.AppLogger;
import com.hjxlog.api.dto.BlogQueryDto;
import com.hjxlog.api.dto.BlogSaveDto;
import com.hjxlog.api.vo.BlogVo;
import com.hjxlog.protocol.PageResult;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.BlogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@RestController
@RequestMapping("/admin/blog")
public class AdminBlogController {

    @Resource
    private BlogService blogService;

    @PostMapping("/list")
    public Result getList(@RequestBody BlogQueryDto dto) {
        Page<BlogVo> blogVos = blogService.getList(dto);
        return Result.success(new PageResult<>(blogVos));
    }

    @GetMapping("/get/{id}")
    public Result getBlogDetail(@PathVariable Integer id) {
        BlogVo blogVo = blogService.getDetail(id);
        return Result.success(blogVo);
    }

    @AppLogger
    @PostMapping("/add")
    public Result addBlog(@Valid @RequestBody BlogSaveDto dto) {
        BlogVo blogVo = blogService.addBlog(dto);
        return Result.success(blogVo);
    }

    @AppLogger
    @PostMapping("/update")
    public Result updateBlog(@Valid @RequestBody BlogSaveDto dto) {
        BlogVo blogVo = blogService.updateBlog(dto);
        return Result.success(blogVo);
    }

    @AppLogger
    @PostMapping("/delete/{id}")
    public Result deleteBlog(@PathVariable Integer id) {
        blogService.deleteBlog(id);
        return Result.success();
    }

}
