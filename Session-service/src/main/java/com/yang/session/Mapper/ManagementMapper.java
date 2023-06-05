package com.yang.session.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ManagementMapper {
    @MapKey("AssetSN")
    List<Map<String,Object>> getAsset();

    List<String> getDeportment();

    List<String> getAssetGroup();

    @MapKey("AssetSN")
    List<Map<String,Object>> getAssetList(Map<String,Object> condition);

    List<String> getLocationList();
}
