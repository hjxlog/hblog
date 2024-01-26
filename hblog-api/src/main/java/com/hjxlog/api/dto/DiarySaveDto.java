package com.hjxlog.api.dto;

import com.hjxlog.enums.DiaryStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@Data
public class DiarySaveDto {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 内容
     */
    @NotEmpty(message = "内容不得为空。")
    private String content;

    /**
     * markdown内容
     */
    @NotEmpty(message = "markdown内容不得为空。")
    private String mdContent;

    /**
     * 状态
     */
    private String status = DiaryStatusEnum.UNPUBLISHED.getCode();

}
