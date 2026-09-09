package com.chx.service;

import com.chx.dto.EmployeeDTO;
import com.chx.dto.EmployeeLoginDTO;
import com.chx.dto.EmployeePageQueryDTO;
import com.chx.dto.EmployeeRegisterDTO;
import com.chx.entity.Employee;
import com.chx.mapper.EmployeeMapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chx.result.PageResult;
import com.chx.vo.EmployeeLoginVO;
import org.springframework.beans.factory.annotation.Autowired;

public interface EmployeeService {


    //登录接口,员工登录
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    //注册接口
    Employee register(EmployeeRegisterDTO employeeRegisterDTO);

    //分页查询
    //page:当前页码
    //pageSize：每页显示的条数
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void disable(Long id);
}
