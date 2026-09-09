package com.chx.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chx.dto.EmployeeLoginDTO;
import com.chx.dto.EmployeePageQueryDTO;
import com.chx.entity.Employee;
import com.chx.vo.EmployeeLoginVO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {
    //根据用户名查询
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    //新增员工
    @Insert("insert into employee ( name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) values ( #{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    int save(Employee employee) ;

    //分页查询
    IPage<Employee> selectPage(IPage<Employee> page, @Param("employeePageQueryDTO") EmployeePageQueryDTO employeePageQueryDTO);

    //给service层返回int，根据影响了多少行来判断是否成功
    @Select("select * from employee where id = #{id}")
    int selectById(Long id);

    @Update("update employee set status = #{disable} where id = #{id}")
    int updateStatus(Long id, Integer disable);
}
