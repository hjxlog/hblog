package com.hjxlog.core.protocol;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@Data
public class PageResult<T> {
    private Long total;
    private List<T> data;

    public PageResult(Long total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public PageResult(Page page) {
        this.total = page.getTotal();
        this.data = page.getRecords();
    }

    public PageResult(Page page, Class clazz) {
        this.total = page.getTotal();
        this.data = BeanUtil.copyToList(page.getRecords(), clazz);
    }

}