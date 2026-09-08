package com.chx.service;

import com.chx.dto.EmployeeLoginDTO;
import com.chx.entity.Employee;
import com.chx.mapper.EmployeeMapper;
import com.chx.vo.EmployeeLoginVO;
import org.springframework.beans.factory.annotation.Autowired;

public interface EmployeeService {


    //登录接口,员工登录
    Employee login(EmployeeLoginDTO employeeLoginDTO);

}
