package com.hjxlog.enums;

/**
 * @author: Huang JX
 * @date: 2024/1/18
 */
public enum DiaryStatusEnum {

    UNPUBLISHED("0", "未发布"),
    PUBLISHED("1", "已发布");

    private final String code;

    private final String name;

    DiaryStatusEnum(String code, String name) {
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
