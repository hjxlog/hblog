package com.hjxlog.api.dto;

import lombok.Data;

/**
 * @author: Huang JX
 * @date: 2023/7/8
 */
@Data
public class BlogQueryServeDto extends PageDto {

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
