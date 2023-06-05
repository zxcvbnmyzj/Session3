package com.yang.session.Service;

import com.yang.session.status.Result;

import javax.websocket.OnClose;
import java.util.Map;

public interface TransferService {

    Result TransferAsset(Map<String, Object> ModifyInformation);
}
