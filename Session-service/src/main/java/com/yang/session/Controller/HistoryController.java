package com.yang.session.Controller;

import com.yang.session.Service.Impl.HistoryServiceImpl;
import com.yang.session.status.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HistoryController {

    @Autowired
    private HistoryServiceImpl historyService;

    @GetMapping("/api/getHistoryList")
    @CrossOrigin
    public @ResponseBody Result getHistoryList(){
        return historyService.getHistoryList();
    }
}
