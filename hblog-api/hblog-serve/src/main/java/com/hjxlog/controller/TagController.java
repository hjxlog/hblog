package com.hjxlog.controller;

import com.hjxlog.domain.Tag;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.TagService;
import com.hjxlog.vo.TagServeVo;
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
@RequestMapping("/tag")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("/list")
    public Result getList() {
        List<Tag> list = tagService.selectPublishedTag();
        return Result.success(list, TagServeVo.class);
    }

}
