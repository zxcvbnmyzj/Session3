package com.yang.session.Pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("employees")
public class Employees {
    private Integer ID;
    private String FirstName;
    private String LastName;
    private String Phone;
    private String Password;
}
