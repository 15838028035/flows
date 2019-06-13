package com.thinkit.cloud.flows.bean;

/**
 * 
 * 自定义基础base实体类VO
 *
 */
public class FlowBaseEntityVO {

    private String processId;
    private String orderId;
    public String getProcessId() {
        return processId;
    }
    public void setProcessId(String processId) {
        this.processId = processId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
}
