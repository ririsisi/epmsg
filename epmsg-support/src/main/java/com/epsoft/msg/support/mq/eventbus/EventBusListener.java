package com.epsoft.msg.support.mq.eventbus;

import com.epsoft.msg.common.domain.TaskInfo;
import com.epsoft.msg.support.domain.MessageTemplate;

import java.util.List;

/**
 * 监听器
 */
public interface EventBusListener {

    /**
     * 消费消息
     */
    void consume(List<TaskInfo> lists);

    /**
     * 撤回消息
     */
    void recall(MessageTemplate messageTemplate);

}
