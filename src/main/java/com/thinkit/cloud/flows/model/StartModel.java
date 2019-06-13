package com.thinkit.cloud.flows.model;

import java.util.Collections;
import java.util.List;

import com.thinkit.cloud.flows.core.Execution;

/**
 * 
 * 开始模型
 *
 */
public class StartModel extends NodeModel {
  /**
   * 
   */
  private static final long serialVersionUID = -4550530562581330477L;

  /**
   * 开始节点无输入变迁
   */
  public List<TransitionModel> getInputs() {
    return Collections.emptyList();
  }

  protected void exec(Execution execution) {
    runOutTransition(execution);
  }
}