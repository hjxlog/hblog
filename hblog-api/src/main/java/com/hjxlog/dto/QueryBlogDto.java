package com.hjxlog.dto;

import lombok.Data;

/**
 * @author: Huang JX
 * @date: 2023/2/27
 */
@Data
public class QueryBlogDto {

    private Integer id;

    private String title;

    private Integer categoryId;

    private Integer tagId;

    private Integer pageNum = 1;

    private Integer pageSize = 10;

}
