package com.yang.session.Service.Impl;

import com.yang.session.DTO.LoginUser;
import com.yang.session.Mapper.EmployeesMapper;
import com.yang.session.Pojo.Employees;
import com.yang.session.Service.LoginService;
import com.yang.session.status.LoginVo;
import com.yang.session.status.ResponseCode;
import com.yang.session.status.Result;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private EmployeesMapper employeesMapper;

    @Override
    public Result login(LoginUser employees) {
        if (StringUtils.isEmpty(employees.getPhone()) || StringUtils.isEmpty(employees.getPassword()))
            return new Result(ResponseCode.BAD_REQUEST,"Account or password cannot be empty",null);
        Employees user = employeesMapper.login(employees.getPhone());
        if (user!=null&&user.getPassword().equals(employees.getPassword())){
            LoginVo loginVo = new LoginVo();
            loginVo.setId(user.getID());
            loginVo.setEmployees(user);
            loginVo.setToken(UUID.randomUUID().toString());
            return new Result(ResponseCode.OK,"Congratulations on successfully logging in!",loginVo);
        }
        return new Result(ResponseCode.UNAUTHORIZED,"Login failed",null);
    }
}
