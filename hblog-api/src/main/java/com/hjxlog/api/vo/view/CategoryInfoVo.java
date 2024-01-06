package com.hjxlog.api.vo.view;

import lombok.Data;

/**
 * @author: Huang JX
 * @date: 2023/12/30
 */
@Data
public class CategoryInfoVo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类下的博客数量
     */
    private Integer blogNum;

}
