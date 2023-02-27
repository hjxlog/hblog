package com.hjxlog.service;

import com.hjxlog.domain.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author hjx
 * @description 针对表【tag】的数据库操作Service
 * @createDate 2023-02-27 08:45:52
 */
public interface TagService extends IService<Tag> {

    List<Tag> selectByBlogId(Long blogId);

}
