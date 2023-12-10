package com.hjxlog.api.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: Huang JX
 * @date: 2023/7/8
 */
@Data
public class BlogServeVo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章概要
     */
    private String summary;

    /**
     * 内容
     */
    private String content;

    /**
     * 浏览量
     */
    private Integer views;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 分类
     */
    private CategoryServeVo category;

    /**
     * 标签
     */
    private List<TagServeVo> tags;

}
