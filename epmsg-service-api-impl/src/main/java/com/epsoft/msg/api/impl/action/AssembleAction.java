package com.epsoft.msg.api.impl.action;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.epsoft.msg.api.impl.domain.SendTaskModel;
import com.epsoft.msg.common.constant.EpmsgConstant;
import com.epsoft.msg.common.domain.TaskInfo;
import com.epsoft.msg.common.dto.model.ContentModel;
import com.epsoft.msg.common.enums.ChannelType;
import com.epsoft.msg.common.enums.RespStatusEnum;
import com.epsoft.msg.common.vo.BasicResultVO;
import com.epsoft.msg.service.api.domain.MessageParam;
import com.epsoft.msg.service.api.enums.BusinessCode;
import com.epsoft.msg.support.dao.MessageTemplateDAO;
import com.epsoft.msg.support.domain.MessageTemplate;
import com.epsoft.msg.support.pipeline.BusinessProcess;
import com.epsoft.msg.support.pipeline.ProcessContext;
import com.epsoft.msg.support.utils.ContentHolderUtil;
import com.epsoft.msg.support.utils.TaskInfoUtils;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
@Service
public class AssembleAction implements BusinessProcess<SendTaskModel> {

    @Autowired
    private MessageTemplateDAO messageTemplateDAO;

    @Override
    public void process(ProcessContext<SendTaskModel> context) {

        SendTaskModel sendTaskModel = context.getProcessModel();
        Long messageTemplateId = sendTaskModel.getMessageTemplateId();

        try {
            Optional<MessageTemplate> messageTemplate = messageTemplateDAO.findById(messageTemplateId);
            if (!messageTemplate.isPresent() || messageTemplate.get().getIsDeleted().equals(EpmsgConstant.TRUE)) {
                context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.TEMPLATE_NOT_FOUND));
                return;
            }

            if (BusinessCode.COMMON_SEND.getCode().equals(context.getCode())) {
                List<TaskInfo> taskInfos = assembleTaskInfo(sendTaskModel,messageTemplate.get());
                sendTaskModel.setTaskInfo(taskInfos);
            } else if(BusinessCode.RECALL.getCode().equals(context.getCode())) {
                sendTaskModel.setMessageTemplate(messageTemplate.get());
            }
        } catch (Exception e) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
            log.error("assemble task fail! templateId:{}，e:{}",messageTemplateId, Throwables.getStackTraceAsString(e));
        }

    }

    /**
     * 组装taskInfo任务消息
     * @param sendTaskModel
     * @param messageTemplate
     * @return
     */
    private List<TaskInfo> assembleTaskInfo(SendTaskModel sendTaskModel, MessageTemplate messageTemplate) {
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();
        ArrayList<TaskInfo> taskInfoList = new ArrayList<>();

        for (MessageParam messageParam : messageParamList) {
            TaskInfo taskInfo = TaskInfo.builder()
                    .messageTemplateId(messageTemplate.getId())
                    .businessId(TaskInfoUtils.generateBusinessId(messageTemplate.getId(),messageTemplate.getTemplateType()))
                    .receiver(new HashSet<>(Arrays.asList(messageParam.getReceiver().split(StrUtil.COMMA))))
                    .idType(messageTemplate.getIdType())
                    .sendChannel(messageTemplate.getSendChannel())
                    .templateType(messageTemplate.getTemplateType())
                    .msgType(messageTemplate.getMsgType())
                    .shieldType(messageTemplate.getShieldType())
                    .sendAccount(messageTemplate.getSendAccount())
                    .contentModel(getContentModelValue(messageTemplate,messageParam))
                    .build();

            taskInfoList.add(taskInfo);
        }
        return taskInfoList;
    }

    /**
     * 获取contentModel，替换msgContent中占位符信息
     * @param messageTemplate
     * @param messageParam
     * @return
     */
    private static ContentModel getContentModelValue(MessageTemplate messageTemplate, MessageParam messageParam) {

        // 得到真正的 ContentModel 类型
        Integer sendChannel = messageTemplate.getSendChannel();
        Class contentModelClass = ChannelType.getChanelModelClassByCode(sendChannel);

        // 得到模板的 msgContent 和入参
        Map<String, String> variables = messageParam.getVariables();
        JSONObject jsonObject = JSON.parseObject(messageTemplate.getMsgContent());

        // 通过 反射 组装出 contentModel
        Field[] fields = ReflectUtil.getFields(contentModelClass);
        ContentModel contentModel = (ContentModel) ReflectUtil.newInstance(contentModelClass);

        for (Field field : fields) {
            String originValue = jsonObject.getString(field.getName());
            if (StrUtil.isNotBlank(originValue)) {
                String resultValue = ContentHolderUtil.replacePlaceHolder(originValue, variables);
                Object resultObj = JSONUtil.isJsonObj(resultValue) ? JSONUtil.toBean(resultValue, field.getType()) : resultValue;
                ReflectUtil.setFieldValue(contentModel,field,resultObj);
            }
        }

        // 如果 url 参数存在，则在url拼接对应的埋点参数
        String url = (String) ReflectUtil.getFieldValue(contentModel,"url");
        if (StrUtil.isNotBlank(url)) {
            String resultUrl = TaskInfoUtils.generateUrl(url, messageTemplate.getId(), messageTemplate.getTemplateType());
            ReflectUtil.setFieldValue(contentModel,"url",resultUrl);
        }

        return contentModel;
    }
}
