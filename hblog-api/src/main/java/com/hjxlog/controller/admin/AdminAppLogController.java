package com.hjxlog.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjxlog.api.dto.AppLogQueryDto;
import com.hjxlog.domain.AppLog;
import com.hjxlog.protocol.PageResult;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.AppLogService;
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
public class AdminAppLogController {

    @Resource
    private AppLogService appLogService;

    @PostMapping("/list")
    public Result getList(@RequestBody AppLogQueryDto dto) {
        Page<AppLog> appLogPage = appLogService.getList(dto);
        return Result.success(new PageResult<>(appLogPage));
    }

}
