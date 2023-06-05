package com.yang.session.Controller;

import com.yang.session.Service.Impl.RegisterServiceImpl;
import com.yang.session.status.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private RegisterServiceImpl registerService;

    @PostMapping("/api/updateAsset")
    @CrossOrigin
    public @ResponseBody Result updateAsset(@RequestBody Map<String,Object> condition){
        return registerService.updateAsset(condition);
    }

    @PostMapping("/api/RegisterAsset")
    @CrossOrigin
    public @ResponseBody Result RegisterAsset(@RequestBody Map<String,Object> condition){
        return registerService.RegisterAsset(condition);
    }
}
