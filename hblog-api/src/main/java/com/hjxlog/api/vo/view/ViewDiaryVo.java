package com.hjxlog.api.vo.view;

import lombok.Data;

import java.util.Date;

/**
 * @author: Huang JX
 * @date: 2024/1/18
 */
@Data
public class ViewDiaryVo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

}
