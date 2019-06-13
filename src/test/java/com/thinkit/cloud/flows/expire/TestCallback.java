package com.thinkit.cloud.flows.expire;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.scheduling.JobCallback;

/**
 * 回调测试
 */
public class TestCallback implements JobCallback {
  private  final Log log = LogFactory.getLog(TestCallback.class);

  public void callback(String taskId, List<FlowTask> newTasks) {
    log.info("callback taskId=" + taskId);
    log.info("newTasks=" + newTasks);
  }
}