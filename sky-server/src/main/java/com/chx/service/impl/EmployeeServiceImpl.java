package com.chx.service.impl;

import com.chx.constant.MessageConstant;
import com.chx.constant.StatusConstant;
import com.chx.dto.EmployeeLoginDTO;
import com.chx.dto.EmployeeRegisterDTO;
import com.chx.entity.Employee;
import com.chx.exception.*;
import com.chx.mapper.EmployeeMapper;
import com.chx.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // 对前端传入的明文密码进行md5加密，再与数据库中的密文比对
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!encryptedPassword.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    @Override
    public Employee register(EmployeeRegisterDTO employeeRegisterDTO) {
        //校验两个密码是否相同
        if (!employeeRegisterDTO.getPassword().equals(employeeRegisterDTO.getConfirmPassword())) {
            throw new PasswordNoConsistent(MessageConstant.PASSWORD_NO_CONSISTENT);
        }

        // 先校验用户名是否已存在，避免重复注册导致数据库唯一键异常
        Employee existingEmployee = employeeMapper.getByUsername(employeeRegisterDTO.getUsername());
        if (existingEmployee != null) {
            throw new BaseException("用户名已存在");
        }

        //对密码进行md5加密
        employeeRegisterDTO.setPassword(DigestUtils.md5DigestAsHex(employeeRegisterDTO.getPassword().getBytes()));

        //将数据插入到数据库
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRegisterDTO, employee);
        employee.setStatus(StatusConstant.ENABLE);

        int result = employeeMapper.save(employee);
        if (result == 0) {
            throw new BaseException("注册失败");
        }
        //返回实体对象
        return employee;
    }

}
