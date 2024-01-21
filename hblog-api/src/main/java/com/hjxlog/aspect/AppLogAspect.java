package com.hjxlog.aspect;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import com.hjxlog.constant.RequestConstants;
import com.hjxlog.constant.SystemConstants;
import com.hjxlog.domain.AppLog;
import com.hjxlog.util.IpAddressUtils;
import com.hjxlog.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
    private RedisUtils redisUtils;

    @Pointcut("@annotation(com.hjxlog.annotation.AppLogger)")
    public void appLoggerPointcut() {
    }

    @Pointcut("execution(* com.hjxlog.controller..*(..))")
    public void serveControllerPointcut() {
    }

    @Around("appLoggerPointcut() || serveControllerPointcut()")
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
            redisUtils.saveList(SystemConstants.APP_LOG_LIST_KEY, appLog);
        }
        return result;
    }

    private AppLog generateAppLog(ProceedingJoinPoint joinPoint) {
        AppLog appLog = new AppLog();
        appLog.setStartTime(new Date());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        appLog.setUri(requestAttributes.getRequest().getRequestURI());
        appLog.setParam(JSONUtil.toJsonStr(joinPoint.getArgs()));
        appLog.setIp(request.getRemoteHost());
        String address = IpAddressUtils.getAddress(request.getRemoteHost());
        appLog.setIpAddress(address);
        String userAgentStr = request.getHeader(RequestConstants.USER_AGENT);
        UserAgent userAgent = UserAgentUtil.parse(userAgentStr);
        appLog.setUserAgent(userAgentStr);
        appLog.setOs(userAgent.getOs().getName());
        appLog.setBrowser(userAgent.getBrowser().getName() + "," + userAgent.getVersion());
        String logUri = appLog.getUri();
        // 访问后台
        String[] uriSplit = logUri.split("/");
        if (logUri.startsWith("/api/admin/")) {
            appLog.setLogType(RequestConstants.TYPE_ADMIN);
            appLog.setModule(uriSplit[3]);
        } else {
            appLog.setLogType(RequestConstants.TYPE_SERVE);
            appLog.setModule(uriSplit[2]);
        }
        return appLog;
    }

}
