package com.epsoft.msg.support.mq.eventbus;

import com.alibaba.fastjson.JSON;
import com.epsoft.msg.common.domain.TaskInfo;
import com.epsoft.msg.support.constant.MessageQueuePipeLine;
import com.epsoft.msg.support.domain.MessageTemplate;
import com.epsoft.msg.support.mq.SendMqService;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(name = "austin.mq.pipeline",havingValue = MessageQueuePipeLine.EVENT_BUS)
public class EventBusSendMqServiceImpl implements SendMqService {

    private EventBus eventBus = new EventBus();

    @Autowired
    private EventBusListener eventBusListener;

    @Value("austin.business.topic.name")
    private String sendTopic;

    @Value("austin.business.recall.topic.name")
    private String recallTopic;

    /**
     *
     * @param topic
     * @param jsonValue
     * @param tagId
     */
    @Override
    public void send(String topic, String jsonValue, String tagId) {
        eventBus.register(eventBusListener);
        if (topic.equals(sendTopic)) {
            eventBus.post(JSON.parseArray(jsonValue, TaskInfo.class));
        } else if (topic.equals(recallTopic)) {
            eventBus.post(JSON.parseArray(jsonValue, MessageTemplate.class));
        }
    }

    @Override
    public void send(String topic, String jsonValue) {
        send(topic,jsonValue,null);
    }
}
