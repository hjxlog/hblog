package com.hjxlog.controller;

import com.hjxlog.domain.Tag;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.TagService;
import com.hjxlog.vo.TagServeVo;
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
@RequestMapping("/tag")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("/list")
    public Result getList() {
        List<Tag> list = tagService.selectPublishedTag();
        return Result.success(list, TagServeVo.class);
    }

    @GetMapping("/getDetail/{id}")
    public Result getDetail(@PathVariable Integer id) {
        List<Tag> list = tagService.selectPublishedTag();
        Tag tag = list.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .get();
        return Result.success(tag, TagServeVo.class);
    }

}