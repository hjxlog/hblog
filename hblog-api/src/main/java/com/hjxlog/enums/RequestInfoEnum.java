package com.hjxlog.enums;

import com.hjxlog.constant.RequestConstants;

/**
 * @author: Huang JX
 * @date: 2023/7/20
 */
public enum RequestInfoEnum {

    // serve blog module
    SERVE_BLOG_LIST(RequestConstants.TYPE_SERVE, RequestConstants.MODULE_BLOG, "/blog/list", RequestConstants.BEHAVIOR_QUERY_LIST),
    SERVE_BLOG_GET(RequestConstants.TYPE_SERVE, RequestConstants.MODULE_BLOG, "/blog/get/", RequestConstants.BEHAVIOR_QUERY_ONE),
    SERVE_BLOG_GET_BY_CATEGORY_ID(RequestConstants.TYPE_SERVE, RequestConstants.MODULE_BLOG, "/blog/getByCategoryId/", RequestConstants.BEHAVIOR_QUERY_LIST),
    SERVE_BLOG_GET_BY_TAG_ID(RequestConstants.TYPE_SERVE, RequestConstants.MODULE_BLOG, "/blog/getByTagId/", RequestConstants.BEHAVIOR_QUERY_LIST),
    // serve category module
    SERVE_CATEGORY_LIST(RequestConstants.TYPE_SERVE, RequestConstants.MODULE_CATEGORY, "/category/list", RequestConstants.BEHAVIOR_QUERY_LIST),
    SERVE_CATEGORY_GET_DETAIL(RequestConstants.TYPE_SERVE, RequestConstants.MODULE_CATEGORY, "/category/getDetail/", RequestConstants.BEHAVIOR_QUERY_ONE),
    // serve tag module
    SERVE_TAG_LIST(RequestConstants.TYPE_SERVE, RequestConstants.MODULE_TAG, "/tag/list", RequestConstants.BEHAVIOR_QUERY_LIST),
    SERVE_TAG_GET_DETAIL(RequestConstants.TYPE_SERVE, RequestConstants.MODULE_TAG, "/tag/getDetail/", RequestConstants.BEHAVIOR_QUERY_ONE),
    // admin blog module
    ADMIN_BLOG_ADD(RequestConstants.TYPE_ADMIN, RequestConstants.MODULE_BLOG, "/api/admin/blog/add", RequestConstants.BEHAVIOR_ADD),
    ADMIN_BLOG_UPDATE(RequestConstants.TYPE_ADMIN, RequestConstants.MODULE_BLOG, "/api/admin/blog/update", RequestConstants.BEHAVIOR_UPDATE),
    ADMIN_BLOG_DELETE(RequestConstants.TYPE_ADMIN, RequestConstants.MODULE_BLOG, "/api/admin/blog/delete/", RequestConstants.BEHAVIOR_DELETE),
    // admin category module
    ADMIN_CATEGORY_ADD(RequestConstants.TYPE_ADMIN, RequestConstants.MODULE_CATEGORY, "/api/admin/category/add", RequestConstants.BEHAVIOR_ADD),
    ADMIN_CATEGORY_UPDATE(RequestConstants.TYPE_ADMIN, RequestConstants.MODULE_CATEGORY, "/api/admin/category/update", RequestConstants.BEHAVIOR_UPDATE),
    ADMIN_CATEGORY_DELETE(RequestConstants.TYPE_ADMIN, RequestConstants.MODULE_CATEGORY, "/api/admin/category/delete/", RequestConstants.BEHAVIOR_DELETE),
    // admin tag module
    ADMIN_TAG_ADD(RequestConstants.TYPE_ADMIN, RequestConstants.MODULE_TAG, "/api/admin/tag/add", RequestConstants.BEHAVIOR_ADD),
    ADMIN_TAG_UPDATE(RequestConstants.TYPE_ADMIN, RequestConstants.MODULE_TAG, "/api/admin/tag/update", RequestConstants.BEHAVIOR_UPDATE),
    ADMIN_TAG_DELETE(RequestConstants.TYPE_ADMIN, RequestConstants.MODULE_TAG, "/api/admin/tag/delete/", RequestConstants.BEHAVIOR_DELETE),
    // admin login module
    ADMIN_LOGIN(RequestConstants.TYPE_ADMIN, RequestConstants.MODULE_LOGIN, "/api/login", RequestConstants.BEHAVIOR_LOGIN),
    ADMIN_LOGOUT(RequestConstants.TYPE_ADMIN, RequestConstants.MODULE_LOGIN, "/api/logout", RequestConstants.BEHAVIOR_LOGOUT),
    // admin user module
    ADMIN_USER_GET_INFO(RequestConstants.TYPE_ADMIN, RequestConstants.MODULE_USER, "/api/admin/user/getInfo", RequestConstants.BEHAVIOR_QUERY_ONE),
    ADMIN_USER_UPDATE_INFO(RequestConstants.TYPE_ADMIN, RequestConstants.MODULE_USER, "/api/admin/user/updateInfo", RequestConstants.BEHAVIOR_UPDATE),
    ADMIN_USER_UPDATE_PASSWORD(RequestConstants.TYPE_ADMIN, RequestConstants.MODULE_USER, "/api/admin/user/updatePassword", RequestConstants.BEHAVIOR_UPDATE),
    ;

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
