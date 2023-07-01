package com.hjxlog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjxlog.api.dto.BlogQueryDto;
import com.hjxlog.api.dto.BlogSaveDto;
import com.hjxlog.api.vo.BlogVo;
import com.hjxlog.domain.Blog;

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

}
