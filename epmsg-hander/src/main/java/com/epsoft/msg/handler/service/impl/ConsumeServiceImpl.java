package com.epsoft.msg.handler.service.impl;

import com.epsoft.msg.common.domain.TaskInfo;
import com.epsoft.msg.handler.pending.TaskPendingHolder;
import com.epsoft.msg.handler.service.ConsumeService;
import com.epsoft.msg.support.domain.MessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumeServiceImpl implements ConsumeService {

    private static final String LOG_BIZ_TYPE = "Receiver#consumer";
    private static final String LOG_BIZ_RECALL_TYPE = "Receiver#recall";

    @Autowired
    private ApplicationContext context;

    @Autowired
    private TaskPendingHolder taskPendingHolder;

    @Override
    public void consumeToSend(List<TaskInfo> lists) {

    }

    @Override
    public void consumeToRecall(MessageTemplate messageTemplate) {

    }
}
