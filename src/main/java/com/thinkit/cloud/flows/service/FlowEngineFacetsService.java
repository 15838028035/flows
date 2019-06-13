package com.thinkit.cloud.flows.service;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowTask;

/**
 * 
 * 流程引擎门面类
 *
 */
public interface FlowEngineFacetsService {

  public FlowEngine getEngine();

  public FlowOrder startInstanceById(String processId, String operator, Map<String, Object> args) throws Exception;

  public FlowOrder startInstanceByName(String name, Long version, String operator, Map<String, Object> args)
      throws Exception;

  public FlowOrder startAndExecute(String name, Long version, String operator, Map<String, Object> args)
      throws Exception;

  public FlowOrder startAndExecute(String processId, String operator, Map<String, Object> args) throws Exception;

  public List<FlowTask> execute(String taskId, String operator, Map<String, Object> args) throws Exception;

  public List<FlowTask> executeAndJump(String taskId, String operator, Map<String, Object> args, String nodeName)
      throws Exception;

  public List<FlowTask> transferMajor(String taskId, String operator, String... actors) throws Exception;

  public List<FlowTask> transferAidant(String taskId, String operator, String... actors) throws Exception;

  public Map<String, Object> flowData(String orderId, String taskName);
}
