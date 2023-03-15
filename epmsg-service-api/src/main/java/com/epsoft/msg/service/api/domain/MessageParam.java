package com.epsoft.msg.service.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * 消息参数
 */
public class MessageParam {

    /**
     * 接收者
     * 【必传】
     */
    private String receiver;

    /**
     * 消息内容中可变的部分（占位符替换）
     * 【可选】
     */
    private Map<String,String> variables;


    /**
     * 扩展参数
     * 【可选】
     */
    private Map<String,String> extra;

}
