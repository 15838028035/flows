package com.thinkit.cloud.flows.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkit.cloud.flows.api.FlowTaskServiceApi;
import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowProcess;
import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.core.Execution;
import com.thinkit.cloud.flows.model.NodeModel;
import com.thinkit.cloud.flows.model.ProcessModel;
import com.thinkit.cloud.flows.model.StartModel;
import com.thinkit.cloud.flows.model.TaskModel;
import com.thinkit.cloud.flows.model.TransitionModel;
import com.thinkit.cloud.flows.service.ConfigurationService;
import com.thinkit.cloud.flows.service.FlowApproveService;
import com.thinkit.cloud.flows.service.FlowCcOrderService;
import com.thinkit.cloud.flows.service.FlowCompletionService;
import com.thinkit.cloud.flows.service.FlowEngine;
import com.thinkit.cloud.flows.service.FlowOrderHistService;
import com.thinkit.cloud.flows.service.FlowOrderService;
import com.thinkit.cloud.flows.service.FlowProcessService;
import com.thinkit.cloud.flows.service.FlowQueryService;
import com.thinkit.cloud.flows.service.FlowTaskAccessStrategyService;
import com.thinkit.cloud.flows.service.FlowTaskActorHistService;
import com.thinkit.cloud.flows.service.FlowTaskActorService;
import com.thinkit.cloud.flows.service.FlowTaskHistService;
import com.thinkit.cloud.flows.service.FlowTaskService;
import com.thinkit.cloud.flows.util.Assert;
import com.zhongkexinli.micro.serv.common.util.StringUtil;

/**
 * 基本的流程引擎实现类
 */
@Service("flowEngine")
public class FlowEngineImpl implements FlowEngine, Serializable {

  private static final Log LOG = LogFactory.getLog(FlowEngineImpl.class);

  /**
   * 配置对象
   */
  @Autowired
  protected ConfigurationService configurationService;
  /**
   * 流程定义业务类
   */
  @Autowired
  protected FlowProcessService flowProcessService;
  /**
   * 流程实例业务类
   */
  @Autowired
  protected FlowOrderService flowOrderService;

  @Autowired
  protected FlowOrderHistService flowOrderHistService;

  @Autowired
  protected FlowCcOrderService flowCcorderService;

  /**
   * 任务业务类
   */
  @Autowired
  protected FlowTaskService flowTaskService;
  @Autowired
  protected FlowTaskServiceApi flowTaskServiceApi;

  @Autowired
  public FlowTaskHistService flowTaskHistService;
  /**
   * 查询业务类
   */
  @Autowired
  protected FlowQueryService flowQueryService;

  /**
   * 任务参与
   */
  @Autowired
  protected FlowTaskActorService flowTaskActorService;
  /**
   * 历史任务参与
   * 
   * @return 历史任务参与
   */
  @Autowired
  public FlowTaskActorHistService flowTaskActorHistService;

  /**
   * 流程完成接口
   */
  @Autowired
  protected FlowCompletionService flowCompletionService;

  @Autowired
  protected FlowTaskAccessStrategyService flowTaskAccessStrategyService;

  @Autowired
  protected FlowApproveService flowApproveService;

  @Override
  public FlowEngine configure(ConfigurationService configurationService) {
    this.configurationService = configurationService;
    return this;
  }

  /**
   * 根据流程定义ID启动流程实例
   */
  public FlowOrder startInstanceById(String id) throws Exception {
    return startInstanceById(id, null, null);
  }

  /**
   * 根据流程定义ID，操作人ID启动流程实例
   */
  public FlowOrder startInstanceById(String id, String operator) throws Exception {
    return startInstanceById(id, operator, null);
  }

  /**
   * 根据流程定义ID，操作人ID，参数列表启动流程实例
   */
  public FlowOrder startInstanceById(String id, String operator, Map<String, Object> args) throws Exception {
    if (args == null) {
      args = new HashMap<String, Object>();
    }
    FlowProcess process = (FlowProcess) flowProcessService().getProcessById(id);
    flowProcessService().check(process, id);
    return startProcess(process, operator, args);
  }

  /**
   * 根据流程名称启动流程实例
   */
  public FlowOrder startInstanceByName(String name) throws Exception {
    return startInstanceByName(name, null, null, null);
  }

  /**
   * 根据流程名称、版本号启动流程实例
   */
  public FlowOrder startInstanceByName(String name, Long version) throws Exception {
    return startInstanceByName(name, version, null, null);
  }

  /**
   * 根据流程名称、版本号、操作人启动流程实例
   */
  public FlowOrder startInstanceByName(String name, Long version, String operator) throws Exception {
    return startInstanceByName(name, version, operator, null);
  }

  /**
   * 根据流程名称、版本号、操作人、参数列表启动流程实例
   */
  public FlowOrder startInstanceByName(String name, Long version, String operator, Map<String, Object> args)
      throws Exception {
    if (args == null) {
      args = new HashMap<String, Object>();
    }
    FlowProcess process = (FlowProcess) flowProcessService().getProcessByVersion(name, version);
    flowProcessService().check(process, name);
    return startProcess(process, operator, args);
  }

  private FlowOrder startProcess(FlowProcess process, String operator, Map<String, Object> args) throws Exception {
    Execution execution = execute(process, operator, args, null, null);
    if (process.getModel() != null) {
      StartModel start = process.getModel().getStart();
      Assert.notNull(start, "流程定义[name=" + process.getFlowName() + ", version=" + process.getFlowVersion() + "]没有开始节点");
      start.execute(execution);
    }

    return execution.getFlowOrder();
  }

  /**
   * 根据父执行对象启动子流程实例（用于启动子流程）
   */
  public FlowOrder startInstanceByExecution(Execution execution) throws Exception {
    FlowProcess process = execution.getProcess();
    StartModel start = process.getModel().getStart();
    Assert.notNull(start, "流程定义[id=" + process.getId() + "]没有开始节点");

    Execution current = execute(process, execution.getOperator(), execution.getArgs(),
        String.valueOf(execution.getParentOrder().getId()), execution.getParentNodeName());
    start.execute(current);
    return current.getFlowOrder();
  }

  /**
   * 创建流程实例，并返回执行对象
   * 
   * @param process
   *          流程定义
   * @param operator
   *          操作人
   * @param args
   *          参数列表
   * @param parentId
   *          父流程实例id
   * @param parentNodeName
   *          启动子流程的父流程节点名称
   * @return Execution
   */
  private Execution execute(FlowProcess process, String operator, Map<String, Object> args, String parentId,
      String parentNodeName) throws Exception {
    FlowOrder order = (FlowOrder) flowOrderService().createFlowOrder(process, operator, args, parentId, parentNodeName);
    if (LOG.isDebugEnabled()) {
      LOG.debug("创建流程实例对象:" + order);
    }
    Execution current = new Execution(this, process, order, args);
    current.setOperator(operator);
    return current;
  }
  
  /**
   * 根据任务主键ID，操作人ID，参数列表完成任务，并且构造执行对象
   * 
   * @param taskId
   *          任务id
   * @param operator
   *          操作人
   * @param args
   *          参数列表
   * @return Execution
   */
  private Execution execute(String taskId, String operator, Map<String, Object> args) throws Exception {
    if (args == null) {
      args = new HashMap<String, Object>();
    }
    FlowTask task = flowTaskServiceApi.complete(taskId, operator, args);
    if (LOG.isDebugEnabled()) {
      LOG.debug("任务[taskId=" + taskId + "]已完成");
    }
    FlowOrder order = flowQueryService().getFlowOrder(task.getFlowOrderId().toString());
    Assert.notNull(order, "指定的流程实例[id=" + task.getFlowOrderId() + "]已完成或不存在");
  // Fixme  order.setUpdateByUname(operator);
 //   order.setUpdateDate(DateUtil.getNowDateYYYYMMddHHMMSS());

    flowOrderService().updateByPrimaryKey(order);
    // 协办任务完成不产生执行对象
    if (!task.isMajor()) {
      return null;
    }
    Map<String, Object> orderMaps = order.getVariableMap();
    if (orderMaps != null) {
      for (Map.Entry<String, Object> entry : orderMaps.entrySet()) {
        if (args.containsKey(entry.getKey())) {
          continue;
        }
        args.put(entry.getKey(), entry.getValue());
      }
    }
    FlowProcess process = (FlowProcess) flowProcessService().getProcessById(String.valueOf(order.getFlowProcessId()));
    Execution execution = new Execution(this, process, order, args);
    execution.setOperator(operator);
    execution.setTask(task);
    return execution;
  }

  /**
   * 根据任务主键ID执行任务
   */
  public List<FlowTask> executeTask(String taskId) throws Exception {
    return executeTask(taskId, null);
  }

  /**
   * 根据任务主键ID，操作人ID执行任务
   */
  public List<FlowTask> executeTask(String taskId, String operator) throws Exception {
    return executeTask(taskId, operator, null);
  }

  /**
   * 根据任务主键ID，操作人ID，参数列表执行任务
   */
  public List<FlowTask> executeTask(String taskId, String operator, Map<String, Object> args) throws Exception {
    // 完成任务，并且构造执行对象
    Execution execution = execute(taskId, operator, args);
    if (execution == null)  {
      return Collections.emptyList();
    }
    ProcessModel model = execution.getProcess().getModel();
    if (model != null) {
      NodeModel nodeModel = model.getNode(execution.getTask().getTaskName());
      // 将执行对象交给该任务对应的节点模型执行
      nodeModel.execute(execution);
    }
    return execution.getTasks();
  }
  
  /**
   * 根据任务主键ID，操作人ID，参数列表执行任务，并且根据nodeName跳转到任意节点 1、nodeName为null时，则驳回至上一步处理 2、nodeName不为null时，则任意跳转，即动态创建转移
   */
  public List<FlowTask> executeAndJumpTask(String taskId, String operator, Map<String, Object> args, String nodeName)
      throws Exception {
    Execution execution = execute(taskId, operator, args);
    if (execution == null)  {
      return Collections.emptyList();
    }
    ProcessModel model = execution.getProcess().getModel();
    Assert.notNull(model, "当前任务未找到流程定义模型");
    if (StringUtil.isBlank(nodeName)) {
      FlowTask newTask = flowTaskServiceApi.rejectTask(model, execution.getTask());
      execution.addTask(newTask);
    } else {
      NodeModel nodeModel = model.getNode(nodeName);
      Assert.notNull(nodeModel, "根据节点名称[" + nodeName + "]无法找到节点模型");
      // 动态创建转移对象，由转移对象执行execution实例
      TransitionModel tm = new TransitionModel();
      tm.setTarget(nodeModel);
      tm.setEnabled(true);
      tm.execute(execution);
    }

    return execution.getTasks();
  }

  /**
   * 根据流程实例ID，操作人ID，参数列表按照节点模型model创建新的自由任务
   */
  public List<FlowTask> createFreeTask(String orderId, String operator, Map<String, Object> args, TaskModel model)
      throws Exception {
    FlowOrder order = flowQueryService.getFlowOrder(orderId);
    Assert.notNull(order, "指定的流程实例[id=" + orderId + "]已完成或不存在");
  //fixme  order.setUpdateByUname(operator);
 //   order.setUpdateDate(DateUtil.getNowDateYYYYMMddHHMMSS());

    FlowProcess process = flowProcessService.getProcessById(String.valueOf(order.getFlowProcessId()));
    Execution execution = new Execution(this, process, order, args);
    execution.setOperator(operator);
    return flowTaskServiceApi.createTask(model, execution);
  }

  public ConfigurationService getConfigurationService() {
    return configurationService;
  }

public FlowProcessService getFlowProcessService() {
	return flowProcessService;
}

public void setFlowProcessService(FlowProcessService flowProcessService) {
	this.flowProcessService = flowProcessService;
}

public FlowOrderService getFlowOrderService() {
	return flowOrderService;
}

public void setFlowOrderService(FlowOrderService flowOrderService) {
	this.flowOrderService = flowOrderService;
}

public FlowOrderHistService getFlowOrderHistService() {
	return flowOrderHistService;
}

public void setFlowOrderHistService(FlowOrderHistService flowOrderHistService) {
	this.flowOrderHistService = flowOrderHistService;
}

public FlowCcOrderService getFlowCcorderService() {
	return flowCcorderService;
}

public void setFlowCcorderService(FlowCcOrderService flowCcorderService) {
	this.flowCcorderService = flowCcorderService;
}

public FlowTaskService getFlowTaskService() {
	return flowTaskService;
}

public void setFlowTaskService(FlowTaskService flowTaskService) {
	this.flowTaskService = flowTaskService;
}

public FlowTaskServiceApi getFlowTaskServiceApi() {
	return flowTaskServiceApi;
}

public void setFlowTaskServiceApi(FlowTaskServiceApi flowTaskServiceApi) {
	this.flowTaskServiceApi = flowTaskServiceApi;
}

public FlowTaskHistService getFlowTaskHistService() {
	return flowTaskHistService;
}

public void setFlowTaskHistService(FlowTaskHistService flowTaskHistService) {
	this.flowTaskHistService = flowTaskHistService;
}

public FlowQueryService getFlowQueryService() {
	return flowQueryService;
}

public void setFlowQueryService(FlowQueryService flowQueryService) {
	this.flowQueryService = flowQueryService;
}

public FlowTaskActorService getFlowTaskActorService() {
	return flowTaskActorService;
}

public void setFlowTaskActorService(FlowTaskActorService flowTaskActorService) {
	this.flowTaskActorService = flowTaskActorService;
}

public FlowTaskActorHistService getFlowTaskActorHistService() {
	return flowTaskActorHistService;
}

public void setFlowTaskActorHistService(FlowTaskActorHistService flowTaskActorHistService) {
	this.flowTaskActorHistService = flowTaskActorHistService;
}

public FlowCompletionService getFlowCompletionService() {
	return flowCompletionService;
}

public void setFlowCompletionService(FlowCompletionService flowCompletionService) {
	this.flowCompletionService = flowCompletionService;
}

public FlowTaskAccessStrategyService getFlowTaskAccessStrategyService() {
	return flowTaskAccessStrategyService;
}

public void setFlowTaskAccessStrategyService(FlowTaskAccessStrategyService flowTaskAccessStrategyService) {
	this.flowTaskAccessStrategyService = flowTaskAccessStrategyService;
}

public FlowApproveService getFlowApproveService() {
	return flowApproveService;
}

public void setFlowApproveService(FlowApproveService flowApproveService) {
	this.flowApproveService = flowApproveService;
}

public void setConfigurationService(ConfigurationService configurationService) {
	this.configurationService = configurationService;
}

@Override
public FlowProcessService flowProcessService() {
	return flowProcessService;
}

@Override
public FlowQueryService flowQueryService() {
	return flowQueryService;
}

@Override
public FlowOrderService flowOrderService() {
	return flowOrderService;
}

@Override
public FlowOrderHistService flowOrderHistService() {
	return flowOrderHistService;
}

@Override
public FlowCcOrderService flowCcOrderService() {
	return flowCcorderService;
}

@Override
public FlowTaskService flowTaskService() {
	return flowTaskService;
}

@Override
public FlowTaskServiceApi flowTaskServiceApi() {
	return flowTaskServiceApi;
}

@Override
public FlowTaskHistService flowTaskHistService() {
	return flowTaskHistService;
}

@Override
public FlowTaskActorService flowTaskActorService() {
	return flowTaskActorService;
}

@Override
public FlowTaskActorHistService flowTaskActorHistService() {
	return null;
}

@Override
public FlowCompletionService flowCompletionService() {
	return flowCompletionService;
}

@Override
public FlowTaskAccessStrategyService flowTaskAccessStrategyService() {
	return flowTaskAccessStrategyService;
}

@Override
public FlowApproveService flowApproveService() {
	return flowApproveService;
}

}
