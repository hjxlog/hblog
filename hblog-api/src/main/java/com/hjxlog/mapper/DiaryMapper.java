package com.hjxlog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjxlog.api.vo.view.DiaryArchiveVo;
import com.hjxlog.domain.Diary;

/**
 * @author hjx
 * @description 针对表【diary】的数据库操作Mapper
 * @createDate 2024-01-18 00:24:15
 * @Entity com.hjxlog.domain.Diary
 */
public interface DiaryMapper extends BaseMapper<Diary> {

    Page<DiaryArchiveVo> getArchiveList(Page<DiaryArchiveVo> page);

}
