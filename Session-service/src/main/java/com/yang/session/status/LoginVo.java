package com.yang.session.status;

import com.yang.session.Pojo.Employees;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LoginVo implements Serializable {
    private static final long serialVersionUID = -1166955294383953060L;
    private Integer id;
    private String token;
    private Employees employees;
}
