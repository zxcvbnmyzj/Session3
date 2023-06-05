package com.yang.session.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RegisterMapper {
    Integer updateAsset(Map<String,Object> condition);

    Integer RegisterAsset(Map<String,Object> AssetInformation);

    Integer updateDepartmentLocation(Map<String,Object> condition);

    Integer addDepartmentLocation(Map<String,Object> condition);

    Integer getDeptIdByDeptName(@Param("deptName") String DeptName);

    Integer getGroupIdByGroupName(@Param("assetgroupName") String AssetGroupName);

    Integer getMaxAssetId();

    Integer getMaxDepartmentLocationID();
}
