package com.epsoft.msg.support.mq.kafka;

import cn.hutool.core.util.StrUtil;
import com.epsoft.msg.support.constant.MessageQueuePipeLine;
import com.epsoft.msg.support.mq.SendMqService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@ConditionalOnProperty(name = "austin.mq.pipeline",havingValue = MessageQueuePipeLine.KAFKA)
public class KafkaSendMqServiceImpl implements SendMqService {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${austin.business.tagId.key}")
    private String tagIdKey;

    @Override
    public void send(String topic, String jsonValue, String tagId) {
        if (StrUtil.isNotBlank(tagId)) {
            List<RecordHeader> headers = Arrays.asList(new RecordHeader(tagIdKey, tagId.getBytes(StandardCharsets.UTF_8)));
            kafkaTemplate.send(new ProducerRecord(topic,null,null,null,jsonValue,headers));
        } else {
            kafkaTemplate.send(topic,jsonValue);
        }
    }

    @Override
    public void send(String topic, String jsonValue) {
        send(topic,jsonValue);
    }
}
