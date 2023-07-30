package com.hjxlog.admin.task;

import cn.hutool.core.collection.CollectionUtil;
import com.hjxlog.core.constant.SystemConstants;
import com.hjxlog.core.domain.AppLog;
import com.hjxlog.core.service.AppLogService;
import com.hjxlog.core.util.RedisUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Huang JX
 * @date: 2023/7/22
 */
@Component
public class AppLogTask {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private AppLogService appLogService;

    @Scheduled(cron = "0 0/10 * * * ?")
    @Transactional
    public void saveAppLogList() {
        System.out.println("执行定时任务");
        List<AppLog> list = redisUtils.getList(SystemConstants.APP_LOG_LIST_KEY, AppLog.class);
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        redisUtils.delete(SystemConstants.APP_LOG_LIST_KEY);
        appLogService.saveBatch(list);
    }

}
