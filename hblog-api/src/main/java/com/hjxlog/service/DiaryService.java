package com.hjxlog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjxlog.api.dto.DiarySaveDto;
import com.hjxlog.api.dto.PageDto;
import com.hjxlog.api.vo.view.DiaryArchiveVo;
import com.hjxlog.domain.Diary;

/**
 * @author hjx
 * @description 针对表【diary】的数据库操作Service
 * @createDate 2024-01-18 00:24:15
 */
public interface DiaryService extends IService<Diary> {

    Page<Diary> getList(PageDto dto);

    Diary getDetail(Integer id);

    Diary addDiary(DiarySaveDto dto);

    Diary updateDiary(DiarySaveDto dto);

    void deleteDiary(Integer id);

    Page<DiaryArchiveVo> getArchiveList(PageDto dto);
}
