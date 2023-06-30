package com.hjxlog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.domain.Category;
import com.hjxlog.mapper.CategoryMapper;
import com.hjxlog.service.CategoryService;
import org.springframework.stereotype.Service;

/**
* @author hjx
* @description 针对表【category】的数据库操作Service实现
* @createDate 2023-06-30 23:33:41
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




