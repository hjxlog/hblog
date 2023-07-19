package com.hjxlog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName app_log
 */
@TableName(value = "app_log")
@Data
public class AppLog implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
     * 访问描述
     */
    private String description;

    /**
     * 请求接口
     */
    private String url;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求参数
     */
    private String param;

    /**
     * ip
     */
    private String ip;

    /**
     * ip所属地址
     */
    private String ipAddress;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 用户名
     */
    private String userName;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}