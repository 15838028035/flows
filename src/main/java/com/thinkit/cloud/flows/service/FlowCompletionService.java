package com.thinkit.cloud.flows.service;

import com.thinkit.cloud.flows.bean.FlowOrderHist;
import com.thinkit.cloud.flows.bean.FlowTaskHist;

/**
 * 任务、实例完成时触发动作的接口
 */
public interface FlowCompletionService {
  /**
   * 任务完成触发执行
   * 
   * @param flowTaskHist  任务对象
   *         
   */
  public void complete(FlowTaskHist flowTaskHist);

  /**
   * 实例完成触发执行
   * 
   * @param flowOrderHist 实例对象
   *          
   */
  public void complete(FlowOrderHist flowOrderHist);
}
