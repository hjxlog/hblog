package com.hjxlog.admin.service;

import com.hjxlog.core.domain.User;
import com.hjxlog.core.protocol.Result;

/**
 * @author: Huang JX
 * @date: 2023/7/5
 */
public interface LoginService {

    /**
     * 登陆
     *
     * @param user
     * @return
     */
    Result login(User user);

    /**
     * 退出登陆
     *
     * @return
     */
    Result logout();

}
