package com.chx.mapper;

import com.chx.dto.EmployeeLoginDTO;
import com.chx.entity.Employee;
import com.chx.vo.EmployeeLoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    //根据用户名查询
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);
}
