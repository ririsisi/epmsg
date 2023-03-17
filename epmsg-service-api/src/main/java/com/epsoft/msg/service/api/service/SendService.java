package com.epsoft.msg.service.api.service;

import com.epsoft.msg.service.api.domain.BatchSendRequest;
import com.epsoft.msg.service.api.domain.SendRequest;
import com.epsoft.msg.service.api.domain.SendResponse;

public interface SendService {

    /**
     * 单文案发送接口
     */
    SendResponse send(SendRequest sendRequest);

    /**
     * 多文案发送接口
     */
    SendResponse batchSend(BatchSendRequest batchSendRequest);

}
