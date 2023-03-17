package com.epsoft.msg.support.pipeline;

public interface BusinessProcess <T extends ProcessModel> {

    /**
     * 真正的处理逻辑
     */
    void process(ProcessContext<T> context);

}
