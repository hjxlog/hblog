package com.hjxlog.controller.view;

import com.hjxlog.api.vo.view.OverviewVo;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.ViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Huang JX
 * @date: 2023/12/30
 */
@RestController
@RequestMapping("/view")
public class ViewController {

    @Resource
    private ViewService viewService;

    @GetMapping("/getOverview")
    public Result getOverview() {
        OverviewVo overview = viewService.getOverview();
        return Result.success(overview);
    }

}
