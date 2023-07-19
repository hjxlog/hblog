package com.hjxlog.aspect;

import cn.hutool.json.JSONUtil;
import com.hjxlog.annotation.AppLogger;
import com.hjxlog.domain.AppLog;
import com.hjxlog.service.AppLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author: Huang JX
 * @date: 2023/7/18
 */
@Component
@Aspect
@Slf4j
public class AppLogAspect {

    @Resource
    private AppLogService appLogService;

    @Pointcut("@annotation(com.hjxlog.annotation.AppLogger)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        AppLog appLog = generateAppLog(joinPoint);
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            appLog.setErrorMsg(e.toString());
            throw e;
        } finally {
            appLog.setEndTime(new Date());
            appLogService.save(appLog);
        }
        return result;
    }

    private AppLog generateAppLog(ProceedingJoinPoint joinPoint) {
        AppLog appLog = new AppLog();
        appLog.setStartTime(new Date());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        AppLogger appLogger = methodSignature.getMethod().getAnnotation(AppLogger.class);
        appLog.setBehavior(appLogger.behavior());
        appLog.setDescription(appLogger.desc());
        appLog.setUrl(String.valueOf(request.getRequestURL()));
        appLog.setMethod(request.getMethod());
        appLog.setParam(JSONUtil.toJsonStr(joinPoint.getArgs()));
        appLog.setIp(request.getRemoteHost());
        return appLog;
    }

}
