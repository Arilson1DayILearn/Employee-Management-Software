package com.software.employeemanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {

    private Long id;

    @NotBlank(message = "Name can't be blank")
    @Size(max = 120, message = "Name size can't be greater than 120")
    private String name;

    @NotBlank(message = "E-mail can't be blank")
    @Size(max = 180, message = "E-mail can't be greater than 180")
    private String email;

    @NotBlank(message = "Job title can't be blank")
    @Size(max = 180, message = "E-mail can't be greater than 180")
    private String jobTitle;

    @NotBlank(message = "Phone can't be blank")
    @Size(max = 20, message = "E-mail can't be greater than 180")
    private String phone;

    @NotBlank(message = "Image url can't be blank")
    @Size(max = 180, message = "E-mail can't be greater than 180")
    private String imageUrl;

    @Size(max = 250)
    private String employeeCode;

}
