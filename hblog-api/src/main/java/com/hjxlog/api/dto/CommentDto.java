package com.hjxlog.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author: Huang JX
 * @date: 2024/1/28
 */
@Data
public class CommentDto {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 网站
     */
    private String website;

    /**
     * 评论内容
     */
    @NotEmpty(message = "评论内容不得为空。")
    private String content;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否为博主
     */
    private Boolean isAdmin;

    /**
     * 父评论id，-1为根评论
     */
    private Integer parentId;

    /**
     * 页面类型
     */
    @NotEmpty(message = "页面类型不得为空。")
    private String pageType;

    /**
     * 页面id
     */
    private Integer pageId;

}
