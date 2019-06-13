package com.thinkit.cloud.flows.model;

/**
 * 自定义任务模型
 */
public class ExtTaskModel extends TaskModel {
  private String assigneeDisplay;

  public String getAssigneeDisplay() {
    return assigneeDisplay;
  }

  public void setAssigneeDisplay(String assigneeDisplay) {
    this.assigneeDisplay = assigneeDisplay;
  }

}
