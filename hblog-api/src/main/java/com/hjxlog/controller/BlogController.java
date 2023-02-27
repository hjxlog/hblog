package com.hjxlog.controller;

import com.hjxlog.dto.QueryBlogDto;
import com.hjxlog.protocol.PageResult;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.BlogService;
import com.hjxlog.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Huang JX
 * @date: 2023/2/27
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/list")
    public Result list(@RequestBody QueryBlogDto dto) {
        Result result = new Result();
        PageResult<BlogVo> list = blogService.getList(dto);
        result.setBody(list);
        return result;
    }

    @GetMapping("/{id}")
    public Result getDetail(@PathVariable("id") Long id){
        return blogService.getDetail(id);
    }

}
