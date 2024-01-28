package com.hjxlog.controller.view;

import com.hjxlog.annotation.AppLogger;
import com.hjxlog.api.dto.CommentDto;
import com.hjxlog.domain.Comment;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: Huang JX
 * @date: 2024/1/28
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @AppLogger
    @PostMapping("/add")
    public Result add(@Valid @RequestBody CommentDto dto) {
        Comment comment = commentService.add(dto);
        return Result.success(comment);
    }

}
