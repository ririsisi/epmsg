package com.epsoft.msg.service.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
/**
 * 发送/撤回 接口的参数
 */
public class SendRequest {

    /**
     * 执行业务类型
     * send:
     */
    private String code;

    /**
     * 消息模板Id
     * send 发送消息
     * recall 撤回消息
     */
    private Long messageTemplateId;

    /**
     * 消息相关参数
     * 当业务类型为send时，必传。
     */
    private MessageParam messageParam;

}
