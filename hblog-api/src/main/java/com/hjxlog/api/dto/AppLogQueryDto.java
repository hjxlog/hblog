package com.hjxlog.api.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author: Huang JX
 * @date: 2023/7/25
 */
@Data
public class AppLogQueryDto extends Page {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 访问模块
     */
    private String module;

    /**
     * 访问行为
     */
    private String behavior;

    /**
     * ip所属地址
     */
    private String ipAddress;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 日志类型
     */
    private String logType;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

}
