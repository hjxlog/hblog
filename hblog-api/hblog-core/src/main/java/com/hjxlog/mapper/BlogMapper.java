package com.hjxlog.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjxlog.api.dto.BlogQueryDto;
import com.hjxlog.api.vo.BlogVo;
import com.hjxlog.domain.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author hjx
 * @description 针对表【blog】的数据库操作Mapper
 * @createDate 2023-06-30 23:33:40
 * @Entity com.hjxlog.domain.Blog
 */
public interface BlogMapper extends BaseMapper<Blog> {

    Page<BlogVo> getList(Page<BlogVo> page, BlogQueryDto dto);

    BlogVo getDetail(Integer id);
}




