package com.hjxlog.api.dto;

import com.hjxlog.enums.BlogStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@Data
public class BlogSaveDto {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 标题
     */
    @NotEmpty(message = "标题不得为空。")
    private String title;

    /**
     * 缩略图
     */
    @NotEmpty(message = "缩略图不得为空。")
    private String thumbnail;

    /**
     * 文章概要
     */
    @NotEmpty(message = "摘要不得为空。")
    private String summary;

    /**
     * 内容
     */
    @NotEmpty(message = "内容不得为空。")
    private String content;

    /**
     * 内容
     */
    @NotEmpty(message = "markdown内容不得为空。")
    private String mdContent;

    /**
     * 状态
     */
    private String status = BlogStatusEnum.UNPUBLISHED.getCode();

    /**
     * 是否推荐
     */
    private Boolean isRecommend = Boolean.FALSE;

    /**
     * 分类
     */
    @NotNull(message = "分类不得为空。")
    private Integer categoryId;

    /**
     * 标签
     */
    @NotNull(message = "标签不得为空。")
    private List<Integer> tagIds;

}
