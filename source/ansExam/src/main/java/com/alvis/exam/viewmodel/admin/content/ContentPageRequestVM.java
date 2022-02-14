package com.alvis.exam.viewmodel.admin.content;

import com.alvis.exam.base.BasePage;
import lombok.Data;

@Data
public class ContentPageRequestVM extends BasePage {

    private Integer id;
    private Integer userLevel;
}
