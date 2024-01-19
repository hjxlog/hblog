package com.hjxlog.api.vo.view;

import lombok.Data;

import java.util.List;

/**
 * @author: Huang JX
 * @date: 2024/1/18
 */
@Data
public class DiaryArchiveVo {

    private String timeLine;

    private List<ViewDiaryVo> viewDiaryVos;

}
