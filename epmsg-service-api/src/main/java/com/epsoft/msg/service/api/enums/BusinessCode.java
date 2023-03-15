package com.epsoft.msg.service.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum BusinessCode {

    COMMON_SEND("send","普通发送"),
    RECALL("recall","撤回消息");

    /**
     * code 关联责任链的模板
     */
    private String code;

    /**
     * 类型说明
     */
    private String msg;
}
