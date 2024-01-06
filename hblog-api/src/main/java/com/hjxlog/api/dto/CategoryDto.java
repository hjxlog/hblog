package com.hjxlog.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@Data
public class CategoryDto extends PageDto {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    @NotEmpty(message = "分类名称不得为空。")
    private String name;

    /**
     * 状态
     */
    @NotNull(message = "分类状态不得为空。")
    private String status;
}
