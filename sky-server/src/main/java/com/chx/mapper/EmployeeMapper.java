package com.chx.mapper;

import com.chx.dto.EmployeeLoginDTO;
import com.chx.entity.Employee;
import com.chx.vo.EmployeeLoginVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    //根据用户名查询
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    //新增员工
    @Insert("insert into employee ( name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) values ( #{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    int save(Employee employee) ;
}
