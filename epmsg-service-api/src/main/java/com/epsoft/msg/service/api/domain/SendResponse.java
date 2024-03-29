package com.epsoft.msg.service.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
/**
 * 接口返回值
 */
public class SendResponse {

    /**
     * 状态状态
     */
    private String code;

    /**
     * 响应编码
     */
    private String msg;

}
