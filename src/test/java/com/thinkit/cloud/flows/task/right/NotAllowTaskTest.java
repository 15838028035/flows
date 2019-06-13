package com.thinkit.cloud.flows.task.right;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.service.FlowEngine;
import com.thinkit.cloud.flows.util.FileUtil;

/**
 * 
 * 任务是否允许执行测试
 *
 */
public class NotAllowTaskTest extends FlowBaseTest {

  @Before
  public void before() {
    processId = flowEngine.flowProcessService()
        .deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/right/flow1.xml"));
  }

  @Test
  public void taskTest() throws Exception {
    Map<String, Object> args = new HashMap<String, Object>();
    args.put("task1.operator", new String[] { "2" });
    FlowOrder order = flowEngine.startInstanceById(processId, "2", args);
    System.out.println("order=" + order);
    List<FlowTask> tasks = flowEngine.flowQueryService().getActiveTasks(String.valueOf(order.getId()));
    for (FlowTask task : tasks) {
      flowEngine.executeTask(task.getId().toString(), FlowEngine.ADMIN, args);
    }
  }

}