package com.hjxlog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.api.dto.CategoryDto;
import com.hjxlog.domain.Blog;
import com.hjxlog.domain.Category;
import com.hjxlog.exception.SystemException;
import com.hjxlog.mapper.BlogMapper;
import com.hjxlog.mapper.CategoryMapper;
import com.hjxlog.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hjx
 * @description 针对表【category】的数据库操作Service实现
 * @createDate 2023-06-30 23:33:41
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private BlogMapper blogMapper;

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
        List<Blog> blogList = blogMapper.selectList(queryWrapper);
        if (CollectionUtil.isNotEmpty(blogList)) {
            throw new SystemException("不可删除有文章关联的分类。");
        }
        return categoryMapper.deleteById(id);
    }
}




