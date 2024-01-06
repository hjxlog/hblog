package com.hjxlog.api.vo.view;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 网站总览信息
 *
 * @author: Huang JX
 * @date: 2023/12/30
 */
@Data
@AllArgsConstructor
public class OverviewVo {

    Long blogNum;

    Long categoryNum;

    Long tagNum;

}
