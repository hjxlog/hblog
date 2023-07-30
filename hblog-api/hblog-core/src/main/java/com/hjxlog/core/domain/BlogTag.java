package com.hjxlog.core.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName blog_tag
 */
@TableName(value = "blog_tag")
@Data
@AllArgsConstructor
public class BlogTag implements Serializable {
    /**
     * 博客id
     */
    private Integer blogId;

    /**
     * 标签id
     */
    private Integer tagId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}