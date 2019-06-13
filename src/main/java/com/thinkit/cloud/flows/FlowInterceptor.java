package com.thinkit.cloud.flows;

import com.thinkit.cloud.flows.core.Execution;

/**
 * 任务拦截器，对产生的任务结果进行拦截
 */
public interface FlowInterceptor {
  /**
   * 拦截方法，参数为执行对象
   * 
   * @param execution
   *          执行对象。可从中获取执行的数据
   */
  public void intercept(Execution execution);
}
