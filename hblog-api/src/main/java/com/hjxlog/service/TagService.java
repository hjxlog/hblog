package com.hjxlog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjxlog.api.dto.TagDto;
import com.hjxlog.domain.Tag;

import java.util.List;

/**
 * @author hjx
 * @description 针对表【tag】的数据库操作Service
 * @createDate 2023-06-30 23:33:41
 */
public interface TagService extends IService<Tag> {

    /**
     * 查询标签列表
     *
     * @param dto
     * @return
     */
    Page<Tag> getList(TagDto dto);

    /**
     * 新增标签
     *
     * @param dto
     * @return
     */
    Tag addTag(TagDto dto);

    /**
     * 修改标签
     *
     * @param dto
     * @return
     */
    Tag updateTag(TagDto dto);

    /**
     * 删除标签
     *
     * @param id
     */
    int deleteTag(Integer id);

    /**
     * 查询已发布的博客集合的标签集合
     *
     * @return
     */
    List<Tag> selectPublishedTag();

    /**
     * 查询标签详情
     *
     * @param id
     * @return
     */
    Tag getDetail(Integer id);
}
