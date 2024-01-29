package com.hjxlog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.api.dto.CommentDto;
import com.hjxlog.domain.Comment;
import com.hjxlog.mapper.CommentMapper;
import com.hjxlog.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hjx
 * @description 针对表【comment】的数据库操作Service实现
 * @createDate 2024-01-26 23:53:40
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public Comment add(CommentDto dto) {
        Comment comment = new Comment();
        BeanUtil.copyProperties(dto, comment);
        commentMapper.insert(comment);
        return comment;
    }

}
