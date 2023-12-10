package com.hjxlog.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author: Huang JX
 * @date: 2023/7/19
 */
@Data
public class UserInfoDto {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不得为空。")
    private String nickname;

}
