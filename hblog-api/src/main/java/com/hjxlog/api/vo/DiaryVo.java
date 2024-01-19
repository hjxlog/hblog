package com.hjxlog.api.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DiaryVo {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 内容
     */
    private String content;

    /**
     * markdown内容
     */
    private String mdContent;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}