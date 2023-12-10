package com.hjxlog.handler;

import cn.hutool.json.JSONUtil;
import com.hjxlog.protocol.Result;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Huang JX
 * @date: 2023/7/5
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result;
        if (authException instanceof InsufficientAuthenticationException) {
            result = Result.fail("401", "请登录后操作");
        } else if (authException instanceof BadCredentialsException) {
            result = Result.fail("503", "用户名或密码错误");
        } else {
            result = Result.fail("500", "认证失败");
        }
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSONUtil.toJsonStr(result));
    }
}
