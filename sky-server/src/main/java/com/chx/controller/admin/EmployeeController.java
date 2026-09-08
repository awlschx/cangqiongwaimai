package com.chx.controller.admin;


import com.chx.dto.EmployeeLoginDTO;
import com.chx.entity.Employee;
import com.chx.properties.JwtProperties;
import com.chx.service.EmployeeService;
import com.chx.vo.EmployeeLoginVO;
import jdk.javadoc.internal.doclets.toolkit.util.DocFinder;
import lombok.extern.slf4j.Slf4j;
import com.chx.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {
    //注入service
    @Autowired
    private EmployeeService employeeService;
    //注入属性
    @Autowired
    private JwtProperties jwtProperties;
//    登录
//    @param
//    EmployeeLoginDTO
//    @return

    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("员工登录，参数：{}",employeeLoginDTO);
        EmployeeLoginVO employeeLoginVO = employeeService.login(employeeLoginDTO);
        return Result.success(employeeLoginVO);
    }
}
