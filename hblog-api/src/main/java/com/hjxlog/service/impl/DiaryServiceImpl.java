package com.hjxlog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.domain.Diary;
import com.hjxlog.mapper.DiaryMapper;
import com.hjxlog.service.DiaryService;
import org.springframework.stereotype.Service;

/**
 * @author hjx
 * @description 针对表【diary】的数据库操作Service实现
 * @createDate 2024-01-18 00:24:15
 */
@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary>
        implements DiaryService {

}
