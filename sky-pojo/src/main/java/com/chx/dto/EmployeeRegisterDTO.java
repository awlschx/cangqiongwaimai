package com.chx.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "员工注册DTO")
public class EmployeeRegisterDTO {
    //用户名
    private String username;
    //id
    private Long id;
    //密码
    private String password;
    //确认密码
    private String confirmPassword;
    //昵称
    private String name;
    //手机号
    private String phone;
    //性别
    private Long sex;
    //身份证号
    private String idNumber;

}
