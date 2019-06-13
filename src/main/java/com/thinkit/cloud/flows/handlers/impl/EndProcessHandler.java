package com.thinkit.cloud.flows.handlers.impl;

import java.util.List;

import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowProcess;
import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.core.Execution;
import com.thinkit.cloud.flows.exceptions.FlowException;
import com.thinkit.cloud.flows.handlers.IHandler;
import com.thinkit.cloud.flows.model.ProcessModel;
import com.thinkit.cloud.flows.model.SubProcessModel;
import com.thinkit.cloud.flows.service.FlowEngine;

/**
 * 结束流程实例的处理器
 */
public class EndProcessHandler implements IHandler {
  /**
   * 结束当前流程实例，如果存在父流程，则触发父流程继续执行
   */
  public void handle(Execution execution) {
    try {
      FlowEngine engine = execution.getEngine();
      FlowOrder order = execution.getFlowOrder();
      List<FlowTask> tasks = engine.flowQueryService().getActiveTasks(String.valueOf(order.getId()));
      for (FlowTask task : tasks) {
        if (task.isMajor()) {
          throw new FlowException("存在未完成的主办任务,请确认.");
        }
        engine.flowTaskServiceApi().complete(task.getId().toString(), FlowEngine.AUTO);
      }
      engine.flowOrderService().complete(order.getId().toString());
      if (order.getParentId()!=null) {
        FlowOrder parentOrder = engine.flowQueryService().getFlowOrder(String.valueOf(order.getParentId()));
        if (parentOrder == null)  {
          return;
        }
        FlowProcess process = engine.flowProcessService().getProcessById(String.valueOf(parentOrder.getFlowProcessId()));
        ProcessModel pm = process.getModel();
        if (pm == null) {
          return;
        }
        SubProcessModel spm = (SubProcessModel) pm.getNode(order.getParentNodeName());
        Execution newExecution = new Execution(engine, process, parentOrder, execution.getArgs());
        newExecution.setChildOrderId(order.getId().toString());
        newExecution.setTask(execution.getTask());
        spm.execute(newExecution);
        // SubProcessModel执行结果的tasks合并到当前执行对象execution的tasks列表中
        execution.addTasks(newExecution.getTasks());
      }
    } catch (Exception e) {
      throw new FlowException(e);
    }
  }
}