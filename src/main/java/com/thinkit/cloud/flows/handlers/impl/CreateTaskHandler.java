package com.thinkit.cloud.flows.handlers.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thinkit.cloud.flows.FlowInterceptor;
import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.core.Execution;
import com.thinkit.cloud.flows.core.ServiceContext;
import com.thinkit.cloud.flows.exceptions.FlowException;
import com.thinkit.cloud.flows.handlers.IHandler;
import com.thinkit.cloud.flows.model.TaskModel;

/**
 * 任务创建操作的处理器
 */
public class CreateTaskHandler implements IHandler {
  private static Log logger = LogFactory.getLog(CreateTaskHandler.class);

  /**
   * 任务模型
   */
  private TaskModel model;

  /**
   * 调用者需要提供任务模型
   *
   * @param model
   *          模型
   */
  public CreateTaskHandler(TaskModel model) {
    this.model = model;
  }

  /**
   * 根据任务模型、执行对象，创建下一个任务，并添加到execution对象的tasks集合中
   */
  public void handle(Execution execution) {
    try {
      List<FlowTask> tasks = execution.getEngine().flowTaskServiceApi().createTask(model, execution);
      execution.addTasks(tasks);
      List<FlowInterceptor> interceptors = ServiceContext.getContext().findList(FlowInterceptor.class);
      try {
        for (FlowInterceptor interceptor : interceptors) {
          interceptor.intercept(execution);
        }
      } catch (Exception e) {
        logger.error("拦截器执行失败=" + e.getMessage());
        throw new FlowException(e);
      }
    } catch (Exception ee) {
      logger.error("拦截器执行失败=" + ee.getMessage());
      throw new FlowException(ee);
    }
  }
}