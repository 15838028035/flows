package com.thinkit.cloud.flows.model;

import com.thinkit.cloud.flows.core.Execution;
import com.thinkit.cloud.flows.handlers.impl.MergeBranchHandler;

/**
 * 合并定义join元素
 */
public class JoinModel extends NodeModel {
  /**
   * 
   */
  private static final long serialVersionUID = 5296621319088076775L;

  /**
   * 执行
   */
  public void exec(Execution execution) {
    fire(new MergeBranchHandler(this), execution);
    if (execution.isMerged()) {
      runOutTransition(execution);
    }
  }
}
