package com.epsoft.msg.handler;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TencentSmsScript {

    public static void send(String phone, String content, String secretId, String secretKey, String sdkAppId) {

        try {
            /**
             * 初始化
             */
            Credential cred = new Credential(secretId, secretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);

            /**
             * 组装入参
             */
            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = new String[]{phone};
            req.setPhoneNumberSet(phoneNumberSet1);
            req.setSmsSdkAppId(sdkAppId);
            req.setSignName("小宋编程公众号");
            req.setTemplateId("1729393");
            String[] templateParamSet1 = {content};
            req.setTemplateParamSet(templateParamSet1);
            req.setSessionContext(IdUtil.fastSimpleUUID());

            /**
             * 发送
             */
            SendSmsResponse response = client.SendSms(req);

            log.info(JSON.toJSONString(response));
        } catch (TencentCloudSDKException e) {
            log.error(Throwables.getStackTraceAsString(e));
        }

    }

    public static void main(String[] args) {

        /**
         * 手机号填自己的
         * 账号信息填自己的
         */
        send("18604999987", "7890", "AKIDUtJZzFJ9lCPUQ8cu2y97waCzpfMq596s", "eotoV0IMCoCqNj25yiHtLbeYIrMgv1vd", "1400800239");
    }
}
