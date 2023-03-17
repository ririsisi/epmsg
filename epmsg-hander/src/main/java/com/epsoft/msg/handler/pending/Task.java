package com.epsoft.msg.handler.pending;

import com.epsoft.msg.common.domain.TaskInfo;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Task 执行器
 * 0：丢弃消息
 * 1：屏蔽消息
 * 2：通用去重功能
 * 3：发送消息
 */
@Data
@Accessors(chain = true)
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Task implements Runnable{

    private TaskInfo taskInfo;

    @Override
    public void run() {
        log.info("task:" + Thread.currentThread().getName());

    }
}
