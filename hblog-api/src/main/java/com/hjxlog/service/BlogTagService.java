package com.hjxlog.service;

import com.hjxlog.domain.BlogTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author hjx
 * @description 针对表【blog_tag】的数据库操作Service
 * @createDate 2023-02-27 08:45:52
 */
public interface BlogTagService extends IService<BlogTag> {

    List<Long> selectTagIdsByBlogId(Long blogId);

}
