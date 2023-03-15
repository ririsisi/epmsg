package com.epsoft.msg.service.api.service;

import com.epsoft.msg.service.api.domain.SendRequest;
import com.epsoft.msg.service.api.domain.SendResponse;

public interface recallService {

    /**
     * 根据模板id撤回消息
     */
    SendResponse recall(SendRequest sendRequest);

}
