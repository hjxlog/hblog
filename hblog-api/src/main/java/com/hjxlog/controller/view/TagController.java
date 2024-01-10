package com.hjxlog.controller.view;

import com.hjxlog.api.vo.TagServeVo;
import com.hjxlog.domain.Tag;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.TagService;
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
        Tag tag = tagService.getDetail(id);
        return Result.success(tag, TagServeVo.class);
    }

}
