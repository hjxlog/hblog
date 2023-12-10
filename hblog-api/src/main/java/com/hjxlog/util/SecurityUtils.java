package com.hjxlog.util;

import com.hjxlog.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author: Huang JX
 * @date: 2023/7/19
 */
public class SecurityUtils {

    public static LoginUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (LoginUser) authentication.getPrincipal();
    }

    public static boolean isCurrentUser(Integer userId) {
        Integer curUserId = getCurrentUser().getUserId();
        return curUserId.equals(userId);
    }

}
