package com.hjxlog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.domain.Comment;
import com.hjxlog.mapper.CommentMapper;
import com.hjxlog.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * @author hjx
 * @description 针对表【comment】的数据库操作Service实现
 * @createDate 2024-01-26 23:53:40
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

}
