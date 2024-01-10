package com.hjxlog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.hjxlog.api.vo.view.OverviewVo;
import com.hjxlog.constant.SystemConstants;
import com.hjxlog.service.BlogService;
import com.hjxlog.service.CategoryService;
import com.hjxlog.service.TagService;
import com.hjxlog.service.ViewService;
import com.hjxlog.util.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Huang JX
 * @date: 2023/12/30
 */
@Service
public class ViewServiceImpl implements ViewService {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private BlogService blogService;

    @Resource
    private TagService tagService;

    @Resource
    private CategoryService categoryService;

    @Override
    public OverviewVo getOverview() {
        OverviewVo vo = (OverviewVo) redisUtils.getValue(SystemConstants.OVERVIEW_VO, OverviewVo.class);
        if (ObjectUtil.isNotEmpty(vo)) {
            return vo;
        }
        long blogNum = blogService.getPublishedBlogNum();
        long categoryNum = categoryService.count();
        long tagNum = tagService.count();
        OverviewVo overviewVo = new OverviewVo(blogNum, categoryNum, tagNum);
        redisUtils.save(SystemConstants.OVERVIEW_VO, overviewVo);
        return overviewVo;
    }
}
