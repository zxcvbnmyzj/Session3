package com.yang.session.Controller;

import com.yang.session.DTO.LoginUser;
import com.yang.session.Pojo.Employees;
import com.yang.session.Service.Impl.LoginServiceImpl;
import com.yang.session.status.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/api/login")
    //跨域
    @CrossOrigin
    public @ResponseBody Result login(@RequestBody LoginUser employees){
        return loginService.login(employees);
    }
}
