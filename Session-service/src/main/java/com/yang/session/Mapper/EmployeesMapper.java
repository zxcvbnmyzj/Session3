package com.yang.session.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.session.Pojo.Employees;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeesMapper extends BaseMapper<Employees> {
    Employees login(@Param("phone") String PhoneNum);

}