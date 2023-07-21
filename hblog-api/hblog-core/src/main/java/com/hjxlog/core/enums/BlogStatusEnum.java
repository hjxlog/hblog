package com.hjxlog.core.enums;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
public enum BlogStatusEnum {

    UNPUBLISHED("0", "未发布"),
    PUBLISHED("1", "已发布");

    private final String code;

    private final String name;

    BlogStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
