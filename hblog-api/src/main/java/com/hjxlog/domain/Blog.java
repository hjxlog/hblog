package com.hjxlog.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName blog
 */
@TableName(value = "blog")
@Data
public class Blog implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

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
     * 状态
     */
    private String status;

    /**
     * 是否置顶
     */
    private Boolean isTop;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 浏览量
     */
    private Integer views;

    /**
     * 分类
     */
    private Integer categoryId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}