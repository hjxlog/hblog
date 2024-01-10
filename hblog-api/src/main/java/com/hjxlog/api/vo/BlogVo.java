package com.hjxlog.api.vo;

import com.hjxlog.domain.Category;
import com.hjxlog.domain.Tag;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@Data
public class BlogVo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 文章概要
     */
    private String summary;

    /**
     * 内容
     */
    private String content;

    /**
     * markdown内容
     */
    private String mdContent;

    /**
     * 状态
     */
    private String status;

    /**
     * 是否推荐
     */
    private Boolean isRecommend;

    /**
     * 浏览量
     */
    private Integer views;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 分类
     */
    private Category category;

    /**
     * 标签
     */
    private List<Tag> tags;

}
