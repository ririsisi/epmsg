package com.epsoft.msg.handler.receiver;

import com.alibaba.fastjson.JSON;
import com.epsoft.msg.common.domain.TaskInfo;
import com.epsoft.msg.support.constant.MessageQueuePipeLine;
import com.epsoft.msg.support.domain.MessageTemplate;
import com.epsoft.msg.support.mq.eventbus.EventBusListener;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@ConditionalOnProperty(name = "austin.mq.pipeline", havingValue = MessageQueuePipeLine.EVENT_BUS)
@Slf4j
@Component
public class EventBusReceiver implements EventBusListener {

    @Override
    @Subscribe
    public void consume(List<TaskInfo> lists) {
        log.error(JSON.toJSONString(lists));
    }

    @Override
    @Subscribe
    public void recall(MessageTemplate messageTemplate) {

    }
}
