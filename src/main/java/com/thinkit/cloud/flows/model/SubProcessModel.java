package com.thinkit.cloud.flows.model;

import com.thinkit.cloud.flows.core.Execution;

/**
 * 子流程定义subprocess元素
 */
public class SubProcessModel extends WorkModel {
  /**
   * 
   */
  private static final long serialVersionUID = -3923955459202018147L;
  /**
   * 子流程名称
   */
  private String processName;
  /**
   * 子流程版本号
   */
  private Long version;
  /**
   * 子流程定义引用
   */
  private ProcessModel subProcess;

  protected void exec(Execution execution) {
    runOutTransition(execution);
  }

  public ProcessModel getSubProcess() {
    return subProcess;
  }

  public void setSubProcess(ProcessModel subProcess) {
    this.subProcess = subProcess;
  }

  public String getProcessName() {
    return processName;
  }

  public void setProcessName(String processName) {
    this.processName = processName;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }
}
