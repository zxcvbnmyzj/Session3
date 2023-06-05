package com.yang.session.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface TransferMapper {
    Integer TransferAsset(Map<String,Object> ModifyInformation);

    Integer updateAssetSN(Map<String,Object> ModifyInformation);
}
