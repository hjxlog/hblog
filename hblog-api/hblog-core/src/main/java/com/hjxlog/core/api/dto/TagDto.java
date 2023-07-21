package com.hjxlog.core.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@Data
public class TagDto extends Page {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    @NotEmpty(message = "标签名称不得为空。")
    private String name;
}
