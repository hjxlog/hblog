package com.hjxlog.api.vo.view;

import lombok.Data;

import java.util.List;

/**
 * @author: Huang JX
 * @date: 2023/12/31
 */
@Data
public class ArchiveVo {

    private Integer year;

    private List<BlogBriefVo> blogBriefVos;

}
