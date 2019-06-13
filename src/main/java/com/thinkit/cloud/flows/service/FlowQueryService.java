package com.thinkit.cloud.flows.service;

import java.util.List;

import com.thinkit.cloud.flows.bean.FlowApprove;
import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowOrderHist;
import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.bean.FlowTaskHist;

/**
 * 
 * 流程查询对象
 *
 */
public interface FlowQueryService {

  public FlowOrder getFlowOrder(String orderId);

  public List<FlowTask> getActiveTasks(String flowOrderId);

  public List<FlowTask> getActiveTasks(int flowOrderId);


  public FlowTask getFlowTask(String taskId);

  public List<FlowApprove> queryApprove(String orderId, String taskId) throws Exception;


  public FlowOrderHist getHistOrder(String orderId);

  public List<FlowTaskHist> getHistoryTasks(String orderId);

  public List<FlowTaskHist> getHistoryTasks(String orderId, String taskName);
}
