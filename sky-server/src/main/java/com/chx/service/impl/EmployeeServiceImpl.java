package com.chx.service.impl;

import com.chx.dto.EmployeeLoginDTO;
import com.chx.entity.Employee;
import com.chx.exception.AccountNotFoundException;
import com.chx.mapper.EmployeeMapper;
import com.chx.vo.EmployeeLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl {
    //注入mapper
    @Autowired
    private EmployeeMapper employeeMapper;
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        //获取用户名和密码
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();
        //从数据库中获取用户信息
        Employee employee = employeeMapper.getByUsername(username);
        //判断用户名和密码是否正确,处理异常（用户名不存在，密码错误，账户冻结）
        //用户名
        if (employee == null){
            //抛出异常
            throw new AccountNotFoundException();
        }
        return   employeeMapper.login(employeeLoginDTO);

    }
}
