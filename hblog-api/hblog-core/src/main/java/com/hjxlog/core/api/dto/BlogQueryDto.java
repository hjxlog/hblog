package com.hjxlog.core.api.dto;

import lombok.Data;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@Data
public class BlogQueryDto extends Page {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 状态
     */
    private String status;

    /**
     * 是否推荐
     */
    private Boolean isRecommend;

    /**
     * 分类Id
     */
    private Integer categoryId;

    /**
     * 标签Id
     */
    private Integer tagId;
}
