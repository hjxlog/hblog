package com.hjxlog.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjxlog.core.api.dto.AppLogQueryDto;
import com.hjxlog.core.domain.AppLog;
import com.hjxlog.core.protocol.PageResult;
import com.hjxlog.core.protocol.Result;
import com.hjxlog.core.service.AppLogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Huang JX
 * @date: 2023/7/25
 */
@RestController
@RequestMapping("/admin/appLog")
public class AppLogController {

    @Resource
    private AppLogService appLogService;

    @PostMapping("/list")
    public Result getList(@RequestBody AppLogQueryDto dto) {
        Page<AppLog> appLogPage = appLogService.getList(dto);
        return Result.success(new PageResult<>(appLogPage));
    }

}
