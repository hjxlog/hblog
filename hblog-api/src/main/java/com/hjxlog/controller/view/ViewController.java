package com.hjxlog.controller.view;

import com.hjxlog.protocol.Result;
import com.hjxlog.service.ViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
        List<Map<String, Object>> list = viewService.getOverview();
        return Result.success(list);
    }

}
