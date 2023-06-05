package com.yang.session.Service.Impl;

import com.yang.session.Config.redis.RedisCache;
import com.yang.session.Mapper.RegisterMapper;
import com.yang.session.Service.RegisterService;
import com.yang.session.Utis.AssetSN;
import com.yang.session.Utis.getNewDate;
import com.yang.session.status.ResponseCode;
import com.yang.session.status.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {


    @Autowired
    private RegisterMapper registerMapper;


    @Override
    public Result updateAsset(Map<String, Object> condition) {
        if (StringUtils.isEmpty((String) condition.get("assetName")))
            return new Result(ResponseCode.BAD_REQUEST, "Please enter information!", null);
        Integer res = registerMapper.updateAsset(condition);
        Integer row = registerMapper.updateDepartmentLocation(condition);
        if (res != 1 || row != 1)
            return new Result(ResponseCode.BAD_REQUEST, "Modification failed!", null);
        return new Result(ResponseCode.OK, "Successfully modified", null);
    }


    public String getAssetSN(String deptName, String assetgroupName) {
        Integer deptId = registerMapper.getDeptIdByDeptName(deptName);
        Integer groupId = registerMapper.getGroupIdByGroupName(assetgroupName);
        return "0" + deptId + "/0" + groupId + "/";
    }

    @Override
    public Result RegisterAsset(Map<String, Object> AssetInformation) {
        if (
                StringUtils.isEmpty(String.valueOf(AssetInformation.get("assetName"))) ||
                        StringUtils.isEmpty(String.valueOf(AssetInformation.get("locationName"))) ||
                        StringUtils.isEmpty(String.valueOf(AssetInformation.get("assetgroupName"))) ||
                        StringUtils.isEmpty(String.valueOf(AssetInformation.get("warranty")))) {
            return new Result(ResponseCode.BAD_REQUEST, "Please enter complete information!", null);
        }
        String deptName = String.valueOf(AssetInformation.get("deptName"));
        String assetgroupName = String.valueOf(AssetInformation.get("assetgroupName"));
        AssetInformation.put("assetSN", getAssetSN(deptName, assetgroupName) + "00" + (registerMapper.getMaxAssetId() + 1));
        AssetInformation.put("empId", 3);
        AssetInformation.put("startDate",getNewDate.getDate());
        Integer row = registerMapper.addDepartmentLocation(AssetInformation);
        Integer res = registerMapper.RegisterAsset(AssetInformation);
        if (res != 1 || row != 1)
            return new Result(ResponseCode.BAD_REQUEST, "Add failed!", null);
        return new Result(ResponseCode.OK, "Added successfully!", null);
    }
}
