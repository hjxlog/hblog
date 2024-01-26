package com.hjxlog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName comment
 */
@TableName(value = "comment")
@Data
public class Comment implements Serializable {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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
    private String content;

    /**
     * 头像
     */
    private String avatar;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 状态
     */
    private String status;

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
    private String pageType;

    /**
     * 页面id
     */
    private Integer pageId;

    /**
     * 评论时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}