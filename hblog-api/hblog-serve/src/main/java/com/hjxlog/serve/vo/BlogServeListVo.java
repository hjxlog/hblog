package com.hjxlog.serve.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author: Huang JX
 * @date: 2023/7/9
 */
@Data
public class BlogServeListVo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间
     */
    private Date createTime;

}
