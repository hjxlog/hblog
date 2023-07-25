package com.hjxlog.admin.service;

import com.hjxlog.core.api.dto.UserInfoDto;
import com.hjxlog.core.api.dto.UserUpdatePasswordDto;
import com.hjxlog.core.api.vo.UserVo;

/**
 * @author: Huang JX
 * @date: 2023/7/23
 */
public interface AdminUserService {

    /**
     * 更新用户信息
     *
     * @param dto
     * @return
     */
    UserVo updateInfo(UserInfoDto dto);

    /**
     * 修改密码
     *
     * @param dto
     * @return
     */
    UserVo updatePassword(UserUpdatePasswordDto dto);

}
