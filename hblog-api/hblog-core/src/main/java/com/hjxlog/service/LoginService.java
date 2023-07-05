package com.hjxlog.service;

import com.hjxlog.domain.User;
import com.hjxlog.protocol.Result;

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

}
