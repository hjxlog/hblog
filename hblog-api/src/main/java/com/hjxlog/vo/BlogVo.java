package com.hjxlog.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hjxlog.domain.Category;
import com.hjxlog.domain.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: Huang JX
 * @date: 2023/2/27
 */
@Data
public class BlogVo implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

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
     * 浏览量
     */
    private Integer views;

    /**
     * 分类
     */
    private Category category;

    /**
     * 标签
     */
    private List<Tag> tags;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}