package com.hjxlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjxlog.domain.BlogTag;

/**
 * @author hjx
 * @description 针对表【blog_tag】的数据库操作Service
 * @createDate 2023-06-30 23:33:41
 */
public interface BlogTagService extends IService<BlogTag> {

    int deleteByBlogId(Integer blogId);

}
