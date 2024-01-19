package com.hjxlog.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjxlog.annotation.AppLogger;
import com.hjxlog.api.dto.DiarySaveDto;
import com.hjxlog.api.dto.PageDto;
import com.hjxlog.domain.Diary;
import com.hjxlog.protocol.PageResult;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.DiaryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: Huang JX
 * @date: 2024/1/18
 */
@RestController
@RequestMapping("/admin/diary")
public class AdminDiaryController {

    @Resource
    private DiaryService diaryService;

    @PostMapping("/list")
    public Result getList(@RequestBody PageDto dto) {
        Page<Diary> result = diaryService.getList(dto);
        return Result.success(new PageResult<>(result));
    }

    @GetMapping("/get/{id}")
    public Result getDetail(@PathVariable Integer id) {
        Diary diary = diaryService.getDetail(id);
        return Result.success(diary);
    }

    @AppLogger
    @PostMapping("/add")
    public Result addDiary(@Valid @RequestBody DiarySaveDto dto) {
        Diary diary = diaryService.addDiary(dto);
        return Result.success(diary);
    }

    @AppLogger
    @PostMapping("/update")
    public Result updateDiary(@Valid @RequestBody DiarySaveDto dto) {
        Diary diary = diaryService.updateDiary(dto);
        return Result.success(diary);
    }

    @AppLogger
    @PostMapping("/delete/{id}")
    public Result deleteDiary(@PathVariable Integer id) {
        diaryService.deleteDiary(id);
        return Result.success();
    }

}
