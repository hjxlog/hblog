package com.hjxlog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjxlog.domain.Blog;
import org.springframework.stereotype.Repository;

/**
 * @author hjx
 * @description 针对表【blog】的数据库操作Mapper
 * @createDate 2023-02-27 08:45:52
 * @Entity com.hjxlog.domain.Blog
 */
@Repository
public interface BlogMapper extends BaseMapper<Blog> {

}




