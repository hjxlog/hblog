package com.hjxlog.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName blog_tag
 */
@TableName(value ="blog_tag")
@Data
public class BlogTag implements Serializable {
    /**
     * 博客id
     */
    @TableId
    private Integer blogId;

    /**
     * 标签id
     */
    @TableId
    private Integer tagId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}