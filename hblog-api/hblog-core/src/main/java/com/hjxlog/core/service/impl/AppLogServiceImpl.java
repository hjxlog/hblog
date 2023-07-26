package com.hjxlog.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.core.api.dto.AppLogQueryDto;
import com.hjxlog.core.constant.RequestConstants;
import com.hjxlog.core.domain.AppLog;
import com.hjxlog.core.mapper.AppLogMapper;
import com.hjxlog.core.service.AppLogService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author hjx
 * @description 针对表【app_log】的数据库操作Service实现
 * @createDate 2023-07-19 23:22:05
 */
@Service
public class AppLogServiceImpl extends ServiceImpl<AppLogMapper, AppLog> implements AppLogService {

    @Override
    public Page<AppLog> getList(AppLogQueryDto dto) {
        Page<AppLog> page = Page.of(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<AppLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(dto.getId()), AppLog::getId, dto.getId());
        queryWrapper.eq(StringUtils.isNotBlank(dto.getModule()), AppLog::getModule, dto.getModule());
        queryWrapper.eq(StringUtils.isNotBlank(dto.getBehavior()), AppLog::getBehavior, dto.getBehavior());
        queryWrapper.like(Objects.nonNull(dto.getIpAddress()), AppLog::getIpAddress, dto.getIpAddress());
        if (RequestConstants.TYPE_ERROR.equals(dto.getLogType())) {
            // 查询错误日志
            queryWrapper.isNotNull(AppLog::getErrorMsg);
        } else {
            queryWrapper.eq(StringUtils.isNotBlank(dto.getLogType()), AppLog::getLogType, dto.getLogType());
        }
        queryWrapper.ge(Objects.nonNull(dto.getStartTime()), AppLog::getStartTime, dto.getStartTime());
        queryWrapper.le(Objects.nonNull(dto.getEndTime()), AppLog::getEndTime, dto.getEndTime());
        queryWrapper.orderByDesc(AppLog::getStartTime);
        Page<AppLog> appLogPage = page(page, queryWrapper);
        return appLogPage;
    }

}
