package com.hjxlog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.domain.Tag;
import com.hjxlog.mapper.TagMapper;
import com.hjxlog.service.TagService;
import org.springframework.stereotype.Service;

/**
* @author hjx
* @description 针对表【tag】的数据库操作Service实现
* @createDate 2023-06-30 23:33:41
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




