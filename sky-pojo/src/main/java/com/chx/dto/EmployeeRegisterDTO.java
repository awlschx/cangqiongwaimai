package com.chx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "员工注册DTO")
public class EmployeeRegisterDTO {
    //用户名
    @ApiModelProperty(value = "用户名", example = "zhangsan", required = true)
    private String username;
    //id
    @ApiModelProperty(value = "id")
    private Long id;
    //密码
    @ApiModelProperty(value = "密码", example = "123456", required = true)
    private String password;
    //确认密码
    @ApiModelProperty(value = "确认密码", example = "123456", required = true)
    private String confirmPassword;
    //昵称
    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;
    //手机号
    @ApiModelProperty(value = "手机号", example = "13812312312")
    private String phone;
    //性别
    @ApiModelProperty(value = "性别", example = "1", notes = "1-男 0-女")
    private String sex;
    //身份证号
    @ApiModelProperty(value = "身份证号", example = "110101199001010047")
    private String idNumber;

}
