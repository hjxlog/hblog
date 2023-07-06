package com.hjxlog.util;

import com.hjxlog.domain.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author: Huang JX
 * @date: 2023/7/6
 */
public class RequestContext {

    public static LoginUser getCurrentUser() {
        return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
