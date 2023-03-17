package com.epsoft.msg.api.impl.action;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.epsoft.msg.api.impl.domain.SendTaskModel;
import com.epsoft.msg.common.enums.RespStatusEnum;
import com.epsoft.msg.common.vo.BasicResultVO;
import com.epsoft.msg.service.api.domain.MessageParam;
import com.epsoft.msg.support.pipeline.BusinessProcess;
import com.epsoft.msg.support.pipeline.ProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PreParamCheckAction implements BusinessProcess<SendTaskModel> {

    /**
     * 最大人人数
     */
    private static final Integer BATCH_RECEIVER_SIZE = 100;
    
    @Override
    public void process(ProcessContext<SendTaskModel> context) {

        SendTaskModel sendTaskModel = context.getProcessModel();
        Long messageTemplateId = sendTaskModel.getMessageTemplateId();
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();

        // 1.没有传入 消息模板id 或者 messageParam
        if(messageTemplateId == null || CollUtil.isEmpty(messageParamList)) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
        }

        // 2.过滤掉receiver = null 的 messageParam
        List<MessageParam> resultMessageParamList = messageParamList.stream()
                .filter(messageParam -> !StrUtil.isBlank(messageParam.getReceiver()))
                .collect(Collectors.toList());

        // 3.过滤掉receiver > 100 的请求
        if (messageParamList.stream().anyMatch(messageParam -> messageParam.getReceiver().split(StrUtil.COMMA).length > BATCH_RECEIVER_SIZE)) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.TOO_MANY_RECEIVER));
        };

    }

    
}
