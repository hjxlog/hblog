package com.hjxlog.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.core.domain.AppLog;
import com.hjxlog.core.mapper.AppLogMapper;
import com.hjxlog.core.service.AppLogService;
import org.springframework.stereotype.Service;

/**
 * @author hjx
 * @description 针对表【app_log】的数据库操作Service实现
 * @createDate 2023-07-19 23:22:05
 */
@Service
public class AppLogServiceImpl extends ServiceImpl<AppLogMapper, AppLog> implements AppLogService {

}