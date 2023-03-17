package com.epsoft.msg.service.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 发送接口的参数
 * Batch
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
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
