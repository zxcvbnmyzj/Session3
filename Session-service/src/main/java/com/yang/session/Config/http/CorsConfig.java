package com.yang.session.Config.http;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        //允许元，这里允许所有的源访问，实际使用会限制
        configuration.addAllowedOrigin("*");
        //允许所有请求tou
        configuration.addAllowedHeader("*");
        //允许所有方法
        configuration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**",configuration);
        return new CorsFilter(source);
    }
}
