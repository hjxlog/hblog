package com.hjxlog.core.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.core.api.dto.CategoryDto;
import com.hjxlog.core.domain.Blog;
import com.hjxlog.core.domain.Category;
import com.hjxlog.core.enums.CategoryStatusEnum;
import com.hjxlog.core.exception.SystemException;
import com.hjxlog.core.mapper.CategoryMapper;
import com.hjxlog.core.service.BlogService;
import com.hjxlog.core.service.CategoryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjx
 * @description 针对表【category】的数据库操作Service实现
 * @createDate 2023-06-30 23:33:41
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Lazy
    @Resource
    private BlogService blogService;

    @Override
    public Page<Category> getList(CategoryDto dto) {
        Page<Category> page = Page.of(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(dto.getName()), Category::getName, dto.getName());
        queryWrapper.eq(StringUtils.isNotBlank(dto.getStatus()), Category::getStatus, dto.getStatus());
        queryWrapper.orderByDesc(Category::getUpdateTime);
        Page<Category> categoryPage = categoryMapper.selectPage(page, queryWrapper);
        return categoryPage;
    }

    @Override
    public Category addCategory(CategoryDto dto) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Category::getName, dto.getName());
        Category category = categoryMapper.selectOne(queryWrapper);
        if (category != null) {
            throw new SystemException("该分类已存在。");
        }
        Category addCategory = new Category();
        BeanUtil.copyProperties(dto, addCategory);
        categoryMapper.insert(addCategory);
        return addCategory;
    }

    @Override
    public Category updateCategory(CategoryDto dto) {
        Category updateCategory = categoryMapper.selectById(dto.getId());
        if (updateCategory == null) {
            throw new SystemException("该分类不存在。");
        }
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Category::getName, dto.getName());
        Category selectOne = categoryMapper.selectOne(queryWrapper);
        if (selectOne != null && !selectOne.getId().equals(dto.getId())) {
            throw new SystemException("该分类已存在。");
        }
        BeanUtil.copyProperties(dto, updateCategory);
        categoryMapper.updateById(updateCategory);
        return updateCategory;
    }

    @Override
    public int deleteCategory(Integer id) {
        if (id == null) {
            throw new SystemException("分类id为空。");
        }
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.select(Blog::getId).eq(Blog::getCategoryId, id);
        List<Blog> blogList = blogService.list(queryWrapper);
        if (CollectionUtil.isNotEmpty(blogList)) {
            throw new SystemException("不可删除有文章关联的分类。");
        }
        return categoryMapper.deleteById(id);
    }

    @Override
    public List<Category> selectColumnsByPublished(SFunction<Category, ?>... columns) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.select(columns);
        queryWrapper.eq(Category::getStatus, CategoryStatusEnum.PUBLISHED.getCode());
        List<Category> list = list(queryWrapper);
        return list;
    }

    @Override
    public List<Category> selectPublishedCategory() {
        List<Category> result = new ArrayList<>();
        // 查询已发布的博客数据
        List<Integer> publishedCategoryIds = blogService.selectColumnsByPublished(Blog::getCategoryId)
                .stream()
                .map(Blog::getCategoryId)
                .distinct()
                .collect(Collectors.toList());
        if (CollectionUtil.isEmpty(publishedCategoryIds)) {
            return result;
        }
        // 根据id查找分类集合
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(Category::getId, publishedCategoryIds);
        result = categoryMapper.selectList(queryWrapper);
        return result;
    }

}




