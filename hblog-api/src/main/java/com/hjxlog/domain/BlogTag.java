package com.hjxlog.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName blog_tag
 */
@TableName(value = "blog_tag")
@Data
public class BlogTag implements Serializable {
    /**
     * 博客id
     */
    private Long blogId;

    /**
     * 标签id
     */
    private Long tagId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}