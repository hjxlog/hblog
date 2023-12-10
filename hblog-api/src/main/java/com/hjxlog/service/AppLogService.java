package com.hjxlog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjxlog.api.dto.AppLogQueryDto;
import com.hjxlog.domain.AppLog;

/**
 * @author hjx
 * @description 针对表【app_log】的数据库操作Service
 * @createDate 2023-07-19 23:22:05
 */
public interface AppLogService extends IService<AppLog> {

    Page<AppLog> getList(AppLogQueryDto dto);

}
