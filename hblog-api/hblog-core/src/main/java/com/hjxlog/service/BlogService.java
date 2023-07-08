package com.hjxlog.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjxlog.api.dto.BlogQueryDto;
import com.hjxlog.api.dto.BlogQueryServeDto;
import com.hjxlog.api.dto.BlogSaveDto;
import com.hjxlog.api.vo.BlogVo;
import com.hjxlog.domain.Blog;

import java.util.List;

/**
 * @author hjx
 * @description 针对表【blog】的数据库操作Service
 * @createDate 2023-06-30 23:33:40
 */
public interface BlogService extends IService<Blog> {

    /**
     * 查询博客列表
     *
     * @param dto
     * @return
     */
    Page<BlogVo> getList(BlogQueryDto dto);

    /**
     * 查询博客详情
     *
     * @param id
     * @return
     */
    BlogVo getDetail(Integer id);

    /**
     * 新增博客
     *
     * @param dto
     * @return
     */
    BlogVo addBlog(BlogSaveDto dto);

    /**
     * 修改博客
     *
     * @param dto
     * @return
     */
    BlogVo updateBlog(BlogSaveDto dto);

    /**
     * 删除博客
     *
     * @param id
     */
    int deleteBlog(Integer id);

    /**
     * 查询已发布的博客数据
     * 1. 博客状态为已发布
     * 2. 分类状态为已发布
     *
     * @param columns
     * @return
     */
    List<Blog> selectColumnsByPublished(SFunction<Blog, ?>... columns);

    /**
     * 分页查询已发布博客
     *
     * @param dto
     * @return
     */
    Page<BlogVo> getPublishedBlogs(BlogQueryServeDto dto);

}
