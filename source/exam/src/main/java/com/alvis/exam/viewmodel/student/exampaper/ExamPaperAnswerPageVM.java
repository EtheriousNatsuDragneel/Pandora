package com.alvis.exam.viewmodel.student.exampaper;

import com.alvis.exam.base.BasePage;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ExamPaperAnswerPageVM extends BasePage {

    private Integer subjectId;

    private Integer createUser;

    private List<Integer> userId;

    private List<String> limitDateTime;

    private Date limitStartTime;

    private Date limitEndTime;

    private String userName;

}
