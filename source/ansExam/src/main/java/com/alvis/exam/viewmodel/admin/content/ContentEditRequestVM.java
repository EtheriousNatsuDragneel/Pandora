package com.alvis.exam.viewmodel.admin.content;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ContentEditRequestVM {

    private Integer id;
    @NotNull
    private Integer userLevel;
    @Digits(integer = 2,fraction = 0)
    private Integer doTime;
    @Min(0)
    private Integer stormNumber;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
