package com.yang.session.Service;

import com.yang.session.DTO.LoginUser;
import com.yang.session.Pojo.Employees;
import com.yang.session.status.Result;

public interface LoginService {

    Result login(LoginUser employees);
}
