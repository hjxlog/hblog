package com.hjxlog.service.impl;

import com.hjxlog.service.BlogService;
import com.hjxlog.service.CategoryService;
import com.hjxlog.service.TagService;
import com.hjxlog.service.ViewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Huang JX
 * @date: 2023/12/30
 */
@Service
public class ViewServiceImpl implements ViewService {

    @Resource
    private BlogService blogService;

    @Resource
    private TagService tagService;

    @Resource
    private CategoryService categoryService;

    @Override
    public List<Map<String, Object>> getOverview() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList.add(createOverviewMap("blog", "文章", blogService.getPublishedBlogNum()));
        resultList.add(createOverviewMap("category", "分类", categoryService.count()));
        resultList.add(createOverviewMap("tag", "标签", tagService.count()));
        return resultList;
    }

    private static Map<String, Object> createOverviewMap(String key, String name, Long num) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("name", name);
        map.put("num", num);
        return map;
    }

}
