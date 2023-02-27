package com.hjxlog.protocol;

import lombok.Data;

import java.util.List;

/**
 * @author: Huang JX
 * @date: 2023/2/27
 */
@Data
public class PageResult<T> {
    private Long total;
    private List<T> data;

    public PageResult(Long total, List<T> data) {
        this.total = total;
        this.data = data;
    }

}
