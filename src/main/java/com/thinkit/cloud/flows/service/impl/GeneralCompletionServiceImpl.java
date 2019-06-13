package com.thinkit.cloud.flows.service.impl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.thinkit.cloud.flows.bean.FlowOrderHist;
import com.thinkit.cloud.flows.bean.FlowTaskHist;
import com.thinkit.cloud.flows.service.FlowCompletionService;

/**
 * 任务、实例完成时触发动作的接口
 */
@Service("flowCompletionService")
public class GeneralCompletionServiceImpl implements FlowCompletionService, Serializable {
  private  final Log log = LogFactory.getLog(GeneralCompletionServiceImpl.class);

  /**
   * 任务完成触发执行
   * 
   * @param flowTaskHist
   *          任务对象
   */
  public void complete(FlowTaskHist flowTaskHist) {
    log.debug("The task[{}] has been user[{}] has completed" + flowTaskHist.getId() + "," + flowTaskHist.getOperator());
  }

  /**
   * 实例完成触发执行
   * 
   * @param flowOrderHist
   *          实例对象
   */
  public void complete(FlowOrderHist flowOrderHist) {
    log.debug("The order[{}] has completed" + flowOrderHist.getId());
  }
}
