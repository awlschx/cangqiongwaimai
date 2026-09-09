package com.chx.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chx.constant.JwtClaimsConstant;
import com.chx.dto.EmployeeDTO;
import com.chx.dto.EmployeeLoginDTO;
import com.chx.dto.EmployeePageQueryDTO;
import com.chx.dto.EmployeeRegisterDTO;
import com.chx.entity.Employee;
import com.chx.properties.JwtProperties;
import com.chx.result.PageResult;
import com.chx.result.Result;
import com.chx.service.EmployeeService;
import com.chx.utils.JwtUtil;
import com.chx.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        //登录之后分发jwt令牌，令牌的作用是在该用户后续的操作的通行证
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    //注册功能
    @PostMapping("/register")
    public Result<String> register(@RequestBody EmployeeRegisterDTO employeeRegisterDTO) {
        log.info("员工注册：{}", employeeRegisterDTO);
        Employee employee = employeeService.register(employeeRegisterDTO);
        return Result.success("注册成功,欢迎你"+employee.getName());
    }

    //分页查询
    @GetMapping("/page")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        PageResult result = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(result);
    }
    }