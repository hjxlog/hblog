package com.hjxlog.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author: Huang JX
 * @date: 2023/7/19
 */
@SpringBootTest
public class SecurityTests {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncodePassword() {
        // 加密
        String password = passwordEncoder.encode("123");
        System.out.println(password);
        // 解密
        System.out.println(passwordEncoder.matches("123", password));
    }

}
