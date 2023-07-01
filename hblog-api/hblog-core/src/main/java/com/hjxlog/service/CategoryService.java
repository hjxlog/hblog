package com.hjxlog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjxlog.api.dto.CategoryDto;
import com.hjxlog.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author hjx
 * @description 针对表【category】的数据库操作Service
 * @createDate 2023-06-30 23:33:41
 */
public interface CategoryService extends IService<Category> {

    /**
     * 查询分类列表
     *
     * @param dto
     * @return
     */
    Page<Category> getList(CategoryDto dto);

    /**
     * 新增分类
     *
     * @param dto
     * @return
     */
    Category addCategory(CategoryDto dto);

    /**
     * 修改分类
     *
     * @param dto
     * @return
     */
    Category updateCategory(CategoryDto dto);

    /**
     * 删除分类
     *
     * @param id
     */
    int deleteCategory(Integer id);

}
