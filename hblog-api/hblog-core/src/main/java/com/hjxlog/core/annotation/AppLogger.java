package com.hjxlog.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统日志器：记录后台操作日志和前台访问日志
 *
 * @author: Huang JX
 * @date: 2023/7/18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AppLogger {
}

