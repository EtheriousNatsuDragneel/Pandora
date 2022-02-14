package com.alvis.exam.viewmodel.admin.exam;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ExamPaperEditAllRequestVM {
    @NotNull
    private Integer suggestTime;

    private List<String> limitDateTime;


}
