package com.epsoft.msg.service.api.domain;

import java.util.List;

/**
 * 发送接口的参数
 * Batch
 */
public class BatchSendRequest {

    /**
     * 业务类型
     * 参数BusinessCode
     */
    private String code;

    /**
     * 消息模板id
     */
    private Long MessageTemplateId;

    /**
     * 消息相关参数
     * 必传
     */
    private List<MessageParam> messageParamList;

}
