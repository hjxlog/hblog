package com.hjxlog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.api.dto.DiarySaveDto;
import com.hjxlog.api.dto.PageDto;
import com.hjxlog.api.vo.view.DiaryArchiveVo;
import com.hjxlog.domain.Diary;
import com.hjxlog.exception.SystemException;
import com.hjxlog.mapper.DiaryMapper;
import com.hjxlog.service.DiaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hjx
 * @description 针对表【diary】的数据库操作Service实现
 * @createDate 2024-01-18 00:24:15
 */
@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary> implements DiaryService {

    @Resource
    private DiaryMapper diaryMapper;

    @Override
    public Page<Diary> getList(PageDto dto) {
        Page<Diary> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        diaryMapper.selectPage(page, null);
        return page;
    }

    @Override
    public Diary getDetail(Integer id) {
        Diary diary = getById(id);
        return diary;
    }

    @Override
    public Diary addDiary(DiarySaveDto dto) {
        Diary diary = new Diary();
        BeanUtil.copyProperties(dto, diary);
        diaryMapper.insert(diary);
        return diary;
    }

    @Override
    public Diary updateDiary(DiarySaveDto dto) {
        Diary diary = diaryMapper.selectById(dto.getId());
        if (diary == null) {
            throw new SystemException("该日记不存在。");
        }
        BeanUtil.copyProperties(dto, diary);
        saveOrUpdate(diary);
        return diary;
    }

    @Override
    public void deleteDiary(Integer id) {
        Diary diary = diaryMapper.selectById(id);
        if (diary == null) {
            throw new SystemException("该日记不存在。");
        }
        diaryMapper.deleteById(id);
    }

    @Override
    public Page<DiaryArchiveVo> getArchiveList(PageDto dto) {
        Page<DiaryArchiveVo> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        diaryMapper.getArchiveList(page);
        return page;
    }

}
