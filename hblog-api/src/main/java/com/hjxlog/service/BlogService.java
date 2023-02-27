package com.hjxlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjxlog.domain.Blog;
import com.hjxlog.dto.QueryBlogDto;
import com.hjxlog.protocol.PageResult;
import com.hjxlog.protocol.Result;
import com.hjxlog.vo.BlogVo;

/**
 * @author hjx
 * @description 针对表【blog】的数据库操作Service
 * @createDate 2023-02-27 08:45:52
 */
public interface BlogService extends IService<Blog> {

    PageResult<BlogVo> getList(QueryBlogDto dto);

    Result getDetail(Long id);
}
