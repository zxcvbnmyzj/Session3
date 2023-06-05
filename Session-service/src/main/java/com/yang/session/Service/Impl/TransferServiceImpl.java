package com.yang.session.Service.Impl;

import com.yang.session.Mapper.HistoryMapper;
import com.yang.session.Mapper.RegisterMapper;
import com.yang.session.Mapper.TransferMapper;
import com.yang.session.Service.TransferService;
import com.yang.session.Utis.AssetSN;
import com.yang.session.Utis.getNewDate;
import com.yang.session.status.ResponseCode;
import com.yang.session.status.Result;
import jdk.management.resource.ResourceMeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Map;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private TransferMapper transferMapper;

    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private HistoryMapper historyMapper;

    public String getAssetSN(String deptName, String assetgroupName) {
        Integer deptId = registerMapper.getDeptIdByDeptName(deptName);
        Integer groupId = registerMapper.getGroupIdByGroupName(assetgroupName);
        return "0" + deptId + "/0" + groupId + "/";
    }


    @Override
    public Result TransferAsset(Map<String, Object> ModifyInformation) {
        String departmentName = String.valueOf(ModifyInformation.get("departmentName"));
        String newLocationName = String.valueOf(ModifyInformation.get("newLocationName"));
        String groupId = String.valueOf(ModifyInformation.get("groupId"));
        if (StringUtils.isEmpty(departmentName) && StringUtils.isEmpty(newLocationName)) {
            return new Result(ResponseCode.BAD_REQUEST, "Please enter complete information", null);
        }
        ModifyInformation.put("deptName", departmentName);
        ModifyInformation.put("locationName", newLocationName);
        String assSN = String.valueOf(ModifyInformation.get("assSN"));
        String[] assetId = assSN.split("/");
        ModifyInformation.put("assetSN", getAssetSN(departmentName, groupId) + assetId[2]);
        ModifyInformation.put("startDate", getNewDate.getDate());
        ModifyInformation.put("transferDate", getNewDate.getDate());
        Integer res = transferMapper.TransferAsset(ModifyInformation);
        Integer row = transferMapper.updateAssetSN(ModifyInformation);
        Integer start = registerMapper.addDepartmentLocation(ModifyInformation);
        Integer maxDepartmentLocationID = registerMapper.getMaxDepartmentLocationID();
        ModifyInformation.put("toDeptLocationnID",maxDepartmentLocationID);
        Integer integer = historyMapper.addHistory(ModifyInformation);
        if (res != 1 || row != 1 || start != 1 || integer != 1) {
            return new Result(ResponseCode.BAD_REQUEST, "Transfer failed", null);
        }
        return new Result(ResponseCode.OK, "Transfer successful", null);
    }
}
