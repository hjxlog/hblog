package com.hjxlog.controller.view;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjxlog.api.dto.BlogQueryServeDto;
import com.hjxlog.api.dto.PageDto;
import com.hjxlog.api.vo.BlogServeListVo;
import com.hjxlog.api.vo.BlogServeVo;
import com.hjxlog.api.vo.BlogVo;
import com.hjxlog.api.vo.view.ArchiveVo;
import com.hjxlog.protocol.PageResult;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.BlogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Huang JX
 * @date: 2023/7/8
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    @PostMapping("/list")
    public Result getList(@RequestBody BlogQueryServeDto dto) {
        Page<BlogVo> blogVos = blogService.getPublishedBlogs(dto);
        return Result.success(new PageResult<>(blogVos, BlogServeVo.class));
    }

    @GetMapping("/get/{id}")
    public Result getBlogDetail(@PathVariable Integer id) {
        BlogVo blogVo = blogService.getDetail(id);
        return Result.success(blogVo, BlogServeVo.class);
    }

    @GetMapping("/getByCategoryId/{categoryId}")
    public Result getListByCategoryId(@PathVariable Integer categoryId) {
        BlogQueryServeDto dto = new BlogQueryServeDto();
        dto.setCategoryId(categoryId);
        Page<BlogVo> blogVos = blogService.getPublishedBlogs(dto);
        return Result.success(new PageResult<>(blogVos, BlogServeListVo.class));
    }

    @GetMapping("/getByTagId/{tagId}")
    public Result getListByTagId(@PathVariable Integer tagId) {
        BlogQueryServeDto dto = new BlogQueryServeDto();
        dto.setTagId(tagId);
        Page<BlogVo> blogVos = blogService.getPublishedBlogs(dto);
        return Result.success(new PageResult<>(blogVos, BlogServeListVo.class));
    }

    @GetMapping("/getRecommendBlogList")
    public Result getRecommendBlogList() {
        List<BlogVo> recommendBlogList = blogService.getRecommendBlogList();
        return Result.success(recommendBlogList);
    }

    @RequestMapping("/getArchiveList")
    public Result getArchiveList(@RequestBody PageDto dto) {
        Page<ArchiveVo> archiveVoList = blogService.getArchiveList(dto);
        return Result.success(new PageResult<>(archiveVoList));
    }

}
