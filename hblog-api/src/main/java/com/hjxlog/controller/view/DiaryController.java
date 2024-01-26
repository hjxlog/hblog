package com.hjxlog.controller.view;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjxlog.api.dto.PageDto;
import com.hjxlog.api.vo.view.DiaryArchiveVo;
import com.hjxlog.protocol.PageResult;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.DiaryService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Huang JX
 * @date: 2024/1/18
 */
@RestController
@RequestMapping("/diary")
public class DiaryController {

    @Resource
    private DiaryService diaryService;

    @RequestMapping("/getArchiveList")
    public Result getArchiveList(@RequestBody PageDto dto) {
        Page<DiaryArchiveVo> archiveVoList = diaryService.getArchiveList(dto);
        return Result.success(new PageResult<>(archiveVoList));
    }

}
