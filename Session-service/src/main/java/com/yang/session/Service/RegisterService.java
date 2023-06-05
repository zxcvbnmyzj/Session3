package com.yang.session.Service;

import com.yang.session.status.Result;

import java.util.Map;

public interface RegisterService {
    Result updateAsset(Map<String,Object> condition);

    Result RegisterAsset(Map<String,Object> AssetInformation);
}
