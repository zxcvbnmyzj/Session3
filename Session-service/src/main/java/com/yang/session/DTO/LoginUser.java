package com.yang.session.DTO;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//数据传输对象
@Data
@Setter
@Getter
public class LoginUser {
    private String phone;
    private String password;
}
