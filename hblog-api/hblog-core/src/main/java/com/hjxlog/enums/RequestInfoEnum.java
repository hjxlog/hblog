package com.hjxlog.enums;

import com.hjxlog.constant.RequestConstants;

/**
 * @author: Huang JX
 * @date: 2023/7/20
 */
public enum RequestInfoEnum {

    SERVE_BLOG_LIST(RequestConstants.TYPE_SERVE, RequestConstants.MODULE_BLOG, "/blog/list", RequestConstants.BEHAVIOR_QUERY_LIST);

    /**
     * 日志类型
     */
    private final String logType;

    /**
     * 访问模块
     */
    private final String module;

    /**
     * 请求接口
     */
    private final String uri;

    /**
     * 访问行为
     */
    private final String behavior;

    RequestInfoEnum(String logType, String module, String uri, String behavior) {
        this.logType = logType;
        this.module = module;
        this.uri = uri;
        this.behavior = behavior;
    }

    public String getLogType() {
        return logType;
    }

    public String getModule() {
        return module;
    }

    public String getUri() {
        return uri;
    }

    public String getBehavior() {
        return behavior;
    }

}
