package com.yang.session.Controller;

import com.yang.session.Service.Impl.ManagementServiceImpl;
import com.yang.session.status.ResponseCode;
import com.yang.session.status.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ManagementController {

    @Autowired
    private ManagementServiceImpl managementService;

    @GetMapping("/api/getAsset")
    @CrossOrigin
    public @ResponseBody Result getAsset(){
        return managementService.getAsset();
    }

    @GetMapping("/api/getDeportment")
    @CrossOrigin
    public @ResponseBody Result getDeportment(){
        return managementService.getDeportment();
    }

    @GetMapping("/api/getAssetGroup")
    @CrossOrigin
    public @ResponseBody Result getAssetGroup(){
        return managementService.getAssetGroup();
    }

    @PostMapping("/api/getAssetList")
    @CrossOrigin
    public @ResponseBody Result getAssetList(@RequestBody Map<String,Object> condition){
        return managementService.getAssetList(condition);
    }

    @GetMapping("/api/getLocationList")
    @CrossOrigin
    public @ResponseBody Result getLocationList(){
        return managementService.getLocationList();
    }
}
