package com.yang.session.Service;

import com.yang.session.status.Result;

import java.util.List;
import java.util.Map;

public interface ManagementService {
    Result getAsset();

    Result getDeportment();

    Result getAssetGroup();

    Result getAssetList(Map<String,Object> condition);

    Result getLocationList();
}
