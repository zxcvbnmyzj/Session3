package com.yang.session.Interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.session.Utis.HttpContextUtil;
import com.yang.session.status.ResponseCode;
import com.yang.session.status.Result;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            setReturn(response, ResponseCode.UNAUTHORIZED,"User not logged in, please log in first!");
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion (HttpServletRequest request,HttpServletResponse response,Object handler,Exception e) throws Exception{

    }

    private static void setReturn(HttpServletResponse response,Integer code,String msg) throws IOException {
        HttpServletResponse servletResponse = response;
        servletResponse.setHeader("Access-Control-Allow-Credentials","true");
        servletResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        servletResponse.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8;");
        Result result = new Result(code,msg,"");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);
        response.getWriter().println(json);
    }

}
