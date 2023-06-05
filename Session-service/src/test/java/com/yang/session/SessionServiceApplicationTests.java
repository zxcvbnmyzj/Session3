package com.yang.session;

import com.yang.session.Config.redis.RedisCache;
import com.yang.session.Mapper.RegisterMapper;
import com.yang.session.Utis.spring.ApplicationContextHolder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class SessionServiceApplicationTests {


    @Autowired
    private RegisterMapper registerMapper;
    @Test
    void contextLoads() {
//        System.out.println(registerMapper.getMaxAssetId());
//
//        System.out.println(registerMapper.getMaxDepartmentLocationID());

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        System.out.println("time: "+time);
    }

}
