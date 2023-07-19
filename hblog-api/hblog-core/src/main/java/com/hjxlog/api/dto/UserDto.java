package com.hjxlog.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author: Huang JX
 * @date: 2023/7/19
 */
@Data
public class UserDto {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不得为空。")
    private String username;

    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不得为空。")
    private String nickname;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不得为空。")
    private String password;

}
