package com.thinkit.cloud.flows.decision.handler;

import com.thinkit.cloud.flows.DecisionHandler;
import com.thinkit.cloud.flows.core.Execution;

/**
 * 决策处理器接口
 */
public class TaskHandler implements DecisionHandler {

  public String decide(Execution execution) {
    return (String) execution.getArgs().get("content");
  }

}
