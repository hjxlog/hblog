package com.hjxlog.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjxlog.annotation.AppLogger;
import com.hjxlog.api.dto.TagDto;
import com.hjxlog.domain.Tag;
import com.hjxlog.protocol.PageResult;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.TagService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@RestController
@RequestMapping("/admin/tag")
public class AdminTagController {

    @Resource
    private TagService tagService;

    @PostMapping("/list")
    public Result getList(@RequestBody TagDto dto) {
        Page<Tag> list = tagService.getList(dto);
        return Result.success(new PageResult<>(list, Tag.class));
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Integer id) {
        Tag Tag = tagService.getById(id);
        return Result.success(Tag);
    }

    @AppLogger
    @PostMapping("/add")
    public Result addTag(@Valid @RequestBody TagDto dto) {
        Tag tag = tagService.addTag(dto);
        return Result.success(tag);
    }

    @AppLogger
    @PostMapping("/update")
    public Result updateTag(@Valid @RequestBody TagDto dto) {
        Tag tag = tagService.updateTag(dto);
        return Result.success(tag);
    }

    @AppLogger
    @PostMapping("/delete/{id}")
    public Result deleteTag(@PathVariable Integer id) {
        tagService.deleteTag(id);
        return Result.success();
    }

}