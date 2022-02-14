package com.alvis.exam.viewmodel.student.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterVM {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;
    @NotBlank
    private String mobile;

//    @NotNull
//    private Integer userLevel;
}
