package com.hjxlog.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: Huang JX
 * @date: 2023/7/23
 */
@Data
public class UserUpdatePasswordDto {

    /**
     * 主键
     */
    @NotNull(message = "用户id不得为空。")
    private Integer id;

    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不得为空。")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不得为空。")
    private String newPassword;

    /**
     * 确认密码
     */
    @NotBlank(message = "确认密码不得为空。")
    private String confirmPassword;

}
