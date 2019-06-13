package com.thinkit.cloud.flows.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkit.cloud.flows.bean.FlowApprove;
import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowOrderHist;
import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.bean.FlowTaskHist;
import com.thinkit.cloud.flows.service.FlowQueryService;
import com.zhongkexinli.micro.serv.common.pagination.Query;

/**
 * 
 * 流程查询对象
 *
 */
@Service("flowQueryService")
public class FlowQueryServiceImpl implements FlowQueryService, Serializable {

  @Autowired
  private FlowEngineFacetsServiceImpl flowEngineFacets;

  public FlowOrder getFlowOrder(String orderId) {
    return (FlowOrder) flowEngineFacets.getEngine().flowOrderService().selectByPrimaryKey(Long.valueOf(orderId));
  }

  /**
   * 流程实例Id
   */
  public List<FlowTask> getActiveTasks(String flowOrderId) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("flowOrderId", flowOrderId);
    return flowEngineFacets.getEngine().flowTaskService().selectByExample(new Query(map));
  }

  /**
   * 活动任务
   */
  public List<FlowTask> getActiveTasks(int flowOrderId) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("flowOrderId", flowOrderId);
    return flowEngineFacets.getEngine().flowTaskService().selectByExample(new Query(map));
  }

  public FlowTask getFlowTask(String taskId) {
    return (FlowTask) flowEngineFacets.getEngine().flowTaskService().selectByPrimaryKey(Long.valueOf(taskId));
  }

  /**
   * 查询审批
   */
  public List<FlowApprove> queryApprove(String orderId, String taskId) throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("flowOrderId", orderId);
   //fixme  map.put("taskId", taskId);
    return flowEngineFacets.getEngine().flowApproveService().selectByExample(new Query(map));
  }

  public FlowOrderHist getHistOrder(String orderId) {
    return (FlowOrderHist) flowEngineFacets.getEngine().flowOrderHistService().selectByPrimaryKey(Long.valueOf(orderId));
  }

  /**
   * 历史任务
   */
  public List<FlowTaskHist> getHistoryTasks(String orderId) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("flowOrderId", orderId);
    return flowEngineFacets.getEngine().flowTaskHistService().selectByExample(new Query(map));
  }

  /**
   * 历史任务
   */
  public List<FlowTaskHist> getHistoryTasks(String orderId, String taskName) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("flowOrderId", orderId);
    map.put("taskName", taskName);
    
    return flowEngineFacets.getEngine().flowTaskHistService().selectByExample(new Query(map));
  }

}
