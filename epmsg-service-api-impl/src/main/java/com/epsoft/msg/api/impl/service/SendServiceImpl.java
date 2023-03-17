package com.epsoft.msg.api.impl.service;

import com.epsoft.msg.api.impl.domain.SendTaskModel;
import com.epsoft.msg.common.vo.BasicResultVO;
import com.epsoft.msg.service.api.domain.BatchSendRequest;
import com.epsoft.msg.service.api.domain.SendRequest;
import com.epsoft.msg.service.api.domain.SendResponse;
import com.epsoft.msg.service.api.service.SendService;
import com.epsoft.msg.support.pipeline.ProcessContext;
import com.epsoft.msg.support.pipeline.ProcessController;
import com.epsoft.msg.support.pipeline.ProcessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SendServiceImpl implements SendService {

    @Autowired
    private ProcessController processController;

    @Override
    public SendResponse send(SendRequest sendRequest) {

        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(sendRequest.getMessageTemplateId())
                .messageParamList(Collections.singletonList(sendRequest.getMessageParam()))
                .build();

        ProcessContext processContext = ProcessContext.builder()
                .code(sendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success()).build();

        ProcessContext process = processController.process(processContext);

        return new SendResponse(process.getResponse().getStatus(), process.getResponse().getMsg());

    }

    @Override
    public SendResponse batchSend(BatchSendRequest batchSendRequest) {

        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(batchSendRequest.getMessageTemplateId())
                .messageParamList(batchSendRequest.getMessageParamList())
                .build();

        ProcessContext context = ProcessContext.builder()
                .code(batchSendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success()).build();

        ProcessContext process = processController.process(context);

        return new SendResponse(process.getResponse().getStatus(), process.getResponse().getMsg());
    }
}
