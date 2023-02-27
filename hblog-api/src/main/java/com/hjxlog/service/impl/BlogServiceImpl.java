package com.hjxlog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.domain.Blog;
import com.hjxlog.domain.Category;
import com.hjxlog.domain.Tag;
import com.hjxlog.dto.QueryBlogDto;
import com.hjxlog.enums.BlogStatusEnum;
import com.hjxlog.mapper.BlogMapper;
import com.hjxlog.protocol.PageResult;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.BlogService;
import com.hjxlog.service.BlogTagService;
import com.hjxlog.service.CategoryService;
import com.hjxlog.service.TagService;
import com.hjxlog.vo.BlogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hjx
 * @description 针对表【blog】的数据库操作Service实现
 * @createDate 2023-02-27 08:45:52
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogTagService blogTagService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据页码，查询博客列表
     *
     * @return
     */
    @Override
    public PageResult<BlogVo> getList(QueryBlogDto dto) {
        Page<Blog> page = Page.of(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blog::getStatus, BlogStatusEnum.PUBLISHED.getCode())
                .eq(dto.getId() != null, Blog::getId, dto.getId())
                .eq(dto.getCategoryId() != null, Blog::getCategoryId, dto.getCategoryId())
                .like(StringUtils.isNotBlank(dto.getTitle()), Blog::getTitle, dto.getTitle())
                .orderByDesc(Blog::getIsTop, Blog::getCreateTime);
        Page<Blog> blogPage = blogMapper.selectPage(page, queryWrapper);
        long total = blogPage.getTotal();
        List<Blog> blogs = blogPage.getRecords();
        List<BlogVo> blogVos = parseBlogVo(blogs);
        PageResult<BlogVo> result = new PageResult<>(total, blogVos);
        return result;
    }


    /**
     * 得到细节
     *
     * @param id id
     * @return {@link Result}
     */
    @Override
    public Result getDetail(Long id) {
        Blog blog = blogMapper.selectById(id);
        if (ObjectUtils.isEmpty(blog)) {

        }
        return null;
    }

    private List<BlogVo> parseBlogVo(List<Blog> blogList) {
        List<BlogVo> blogVos = new ArrayList<>();
        for (Blog blog : blogList) {
            BlogVo blogVo = new BlogVo();
            BeanUtils.copyProperties(blog, blogVo);
            List<Tag> tags = tagService.selectByBlogId(blog.getId());
            blogVo.setTags(tags);
            Category category = categoryService.getById(blog.getCategoryId());
            blogVo.setCategory(category);
            blogVos.add(blogVo);
        }
        return blogVos;
    }

}




