package com.hjxlog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.api.dto.BlogQueryDto;
import com.hjxlog.api.dto.BlogSaveDto;
import com.hjxlog.api.vo.BlogVo;
import com.hjxlog.domain.Blog;
import com.hjxlog.domain.BlogTag;
import com.hjxlog.domain.Category;
import com.hjxlog.domain.Tag;
import com.hjxlog.exception.SystemException;
import com.hjxlog.mapper.BlogMapper;
import com.hjxlog.service.BlogService;
import com.hjxlog.service.BlogTagService;
import com.hjxlog.service.CategoryService;
import com.hjxlog.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hjx
 * @description 针对表【blog】的数据库操作Service实现
 * @createDate 2023-06-30 23:33:40
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private BlogTagService blogTagService;

    @Resource
    private TagService tagService;

    @Resource
    private CategoryService categoryService;

    @Override
    public Page<BlogVo> getList(BlogQueryDto dto) {
        Page<BlogVo> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        blogMapper.getList(page, dto);
        return page;
    }

    @Override
    public BlogVo getDetail(Integer id) {
        return blogMapper.getDetail(id);
    }

    @Override
    @Transactional
    public BlogVo addBlog(BlogSaveDto dto) {
        Blog blog = new Blog();
        BeanUtil.copyProperties(dto, blog);
        blogMapper.insert(blog);
        BlogVo blogVo = handleCategoryAndTags(blog, dto.getCategoryId(), dto.getTagIds());
        return blogVo;
    }

    @Override
    @Transactional
    public BlogVo updateBlog(BlogSaveDto dto) {
        Blog blog = blogMapper.selectById(dto.getId());
        if (blog == null) {
            throw new SystemException("该博客不存在。");
        }
        BeanUtil.copyProperties(dto, blog);
        saveOrUpdate(blog);
        BlogVo blogVo = handleCategoryAndTags(blog, dto.getCategoryId(), dto.getTagIds());
        return blogVo;
    }

    @Override
    @Transactional
    public int deleteBlog(Integer id) {
        Blog blog = blogMapper.selectById(id);
        if (blog == null) {
            throw new SystemException("该博客不存在。");
        }
        blogTagService.deleteByBlogId(id);
        return blogMapper.deleteById(id);
    }

    /**
     * 维护分类和标签列表
     *
     * @param blog       博客实体
     * @param categoryId 分类id
     * @param tagIds     标签id列表
     * @return
     */
    private BlogVo handleCategoryAndTags(Blog blog, Integer categoryId, List<Integer> tagIds) {
        Category category = categoryService.getById(categoryId);
        if (category == null) {
            throw new SystemException("该分类不存在。");
        }
        blogTagService.deleteByBlogId(blog.getId());
        List<Tag> tagList = tagService.listByIds(tagIds);
        List<BlogTag> blogTagList = new ArrayList<>();
        tagList.stream().filter(tag -> tag != null).forEach(tag -> blogTagList.add(new BlogTag(blog.getId(), tag.getId())));
        if (blogTagList.size() > 0) {
            blogTagService.saveBatch(blogTagList);
        }
        BlogVo blogVo = new BlogVo();
        BeanUtil.copyProperties(blog, blogVo);
        blogVo.setCategory(category);
        blogVo.setTags(tagList);
        return blogVo;
    }
}




