package com.epsoft.msg.support.pipeline;

import java.util.List;

/**
 * 任务执行模板，将责任链穿起来
 */
public class ProcessTemplate {

    private List<BusinessProcess> processList;

    public List<BusinessProcess> getProcessList() {
        return processList;
    }

    public void setProcessList(List<BusinessProcess> processList) {
        this.processList = processList;
    }

}
