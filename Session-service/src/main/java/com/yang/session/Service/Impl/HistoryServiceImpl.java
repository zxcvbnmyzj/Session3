package com.yang.session.Service.Impl;

import com.yang.session.Mapper.HistoryMapper;
import com.yang.session.Service.HistoryService;
import com.yang.session.status.ResponseCode;
import com.yang.session.status.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public Result getHistoryList() {
        List<Map<String, Object>> res = historyMapper.getHistoryList();
        if (res == null){
            return new Result(ResponseCode.BAD_REQUEST,"Server error!",null);
        }
        return new Result(ResponseCode.OK,"success!",res);
    }


}
