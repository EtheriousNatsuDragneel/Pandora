package com.alvis.exam.viewmodel.student.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateVM {

    @NotBlank
    private String realName;

    private String age;

    private Integer sex;

    private String birthDay;

    private String phone;

//    @NotNull
    private Integer userLevel;
}
