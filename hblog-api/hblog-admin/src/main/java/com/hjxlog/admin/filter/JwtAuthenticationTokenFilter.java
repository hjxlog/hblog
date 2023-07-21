package com.hjxlog.admin.filter;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.hjxlog.admin.domain.LoginUser;
import com.hjxlog.core.constant.AdminConstants;
import com.hjxlog.core.protocol.Result;
import com.hjxlog.core.util.WebUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author: Huang JX
 * @date: 2023/7/5
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        String username;
        try {
            JWT jwt = JWTUtil.parseToken(token);
            username = jwt.getPayload("username").toString();
        } catch (Exception e) {
            throw new RuntimeException("parseToken exception", e);
        }
        // 从redis中获取用户信息
        LoginUser loginUser = JSONUtil.toBean(stringRedisTemplate.opsForValue().get(AdminConstants.REDIS_SIGN_LOGIN_USER + username), LoginUser.class);
        if (Objects.isNull(loginUser.getUser())) {
            Result result = Result.fail("401", "请登录后操作");
            WebUtils.renderString(response, JSONUtil.toJsonStr(result));
            return;
        }
        // 将封装的authentication放到SecurityContext中
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
