package com.yang.session.Controller;

import com.yang.session.Service.Impl.TransferServiceImpl;
import com.yang.session.status.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TransferController {

    @Autowired
    private TransferServiceImpl transferService;

    @PostMapping("/api/transferAsset")
    @CrossOrigin
    public @ResponseBody Result TransferAsset(@RequestBody Map<String,Object> ModifyInformation){
        return transferService.TransferAsset(ModifyInformation);
    }

}
