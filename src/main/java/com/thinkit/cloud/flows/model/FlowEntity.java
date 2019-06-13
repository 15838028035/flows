package com.thinkit.cloud.flows.model;

/**
 * 
 * 流程实体
 *
 */
public class FlowEntity extends BaseModel {

  // 流程实例ID
  protected String orderId;
  // 任务ID
  protected String taskId;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

}
