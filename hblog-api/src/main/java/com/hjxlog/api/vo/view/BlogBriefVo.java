package com.hjxlog.api.vo.view;

import com.hjxlog.domain.Category;
import com.hjxlog.domain.Tag;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 博客概要内容Vo
 *
 * @author: Huang JX
 * @date: 2023/12/31
 */
@Data
public class BlogBriefVo {

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
    private Category category;

    /**
     * 标签
     */
    private List<Tag> tags;

}
