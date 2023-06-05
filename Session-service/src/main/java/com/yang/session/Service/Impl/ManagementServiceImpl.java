package com.yang.session.Service.Impl;

import com.yang.session.Mapper.ManagementMapper;
import com.yang.session.Service.ManagementService;
import com.yang.session.status.ResponseCode;
import com.yang.session.status.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManagementServiceImpl implements ManagementService {

    @Autowired
    private ManagementMapper managementMapper;

    @Override
    public Result getAsset() {
        return new Result(ResponseCode.OK,"",managementMapper.getAsset());
    }

    @Override
    public Result getDeportment() {
        return new Result(ResponseCode.OK,"",managementMapper.getDeportment());
    }

    @Override
    public Result getAssetGroup() {
        return new Result(ResponseCode.OK,"",managementMapper.getAssetGroup());
    }

    @Override
    public Result getAssetList(Map<String,Object> condition) {
        if (condition == null)
            return new Result(ResponseCode.BAD_REQUEST,"Please select your information!",null);
        return new Result(ResponseCode.OK,"",managementMapper.getAssetList(condition));
    }

    @Override
    public Result getLocationList() {
        return new Result(ResponseCode.OK,"",managementMapper.getLocationList());
    }
}
