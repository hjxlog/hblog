package com.hjxlog.core.api.dto;

import lombok.Data;

/**
 * @author: Huang JX
 * @date: 2023/7/8
 */
@Data
public class BlogQueryServeDto extends Page {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 分类
     */
    private Integer categoryId;

    /**
     * 标签
     */
    private Integer tagId;

}
