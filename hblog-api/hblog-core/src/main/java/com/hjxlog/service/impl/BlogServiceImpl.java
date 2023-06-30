package com.hjxlog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.domain.Blog;
import com.hjxlog.mapper.BlogMapper;
import com.hjxlog.service.BlogService;
import org.springframework.stereotype.Service;

/**
* @author hjx
* @description 针对表【blog】的数据库操作Service实现
* @createDate 2023-06-30 23:33:40
*/
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog>
    implements BlogService{

}




