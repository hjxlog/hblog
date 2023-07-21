package com.hjxlog.core.api.dto;

import lombok.Data;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@Data
public class Page {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    public Integer getPageNum() {
        return pageNum <= 0 ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize <= 0 ? 10 : pageSize;
    }
}
