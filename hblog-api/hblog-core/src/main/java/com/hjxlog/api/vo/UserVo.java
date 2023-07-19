package com.hjxlog.api.vo;

import lombok.Data;

/**
 * @author: Huang JX
 * @date: 2023/7/19
 */
@Data
public class UserVo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

}
