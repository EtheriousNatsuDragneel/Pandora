package com.alvis.exam.viewmodel.admin.content;

import com.alvis.exam.viewmodel.BaseVM;
import lombok.Data;

@Data
public class ContentResponseVM extends BaseVM {

    private Long id;

    private String createTime;

    private Integer userLevel;

    private Integer doTime;

    private Integer stormNumber;

    private String title;

}
