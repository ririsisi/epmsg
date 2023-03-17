package com.epsoft.msg.handler.service;

import com.epsoft.msg.common.domain.TaskInfo;
import com.epsoft.msg.support.domain.MessageTemplate;

import java.util.List;

/**
 * 消费消息服务
 */
public interface ConsumeService {

    /**
     * 从MQ拉到消息进行消费，发送消息
     */
    void consumeToSend(List<TaskInfo> lists);

    /**
     * 从MQ拉到消息进行消费，撤回消息
     */
    void consumeToRecall(MessageTemplate messageTemplate);

}
