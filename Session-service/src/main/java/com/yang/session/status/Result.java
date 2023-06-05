package com.yang.session.status;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;

@Data
@AllArgsConstructor
public class Result {
    private Integer code;
    private String message;
    private Object data;
}
