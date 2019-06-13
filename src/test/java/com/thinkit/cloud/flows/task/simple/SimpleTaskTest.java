package com.thinkit.cloud.flows.task.simple;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowProcess;
import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.util.FileUtil;

/**
 * 测试简单流程
 *
 */
public class SimpleTaskTest extends FlowBaseTest {
  @Before
  public void before() throws Exception {
    processId = flowEngine.flowProcessService()
        .deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/simple/flow1.xml"));
  }

  @Test
  public void taskTest() throws Exception {
    FlowProcess flowProcess = (FlowProcess) flowEngine.flowProcessService().getProcessById(processId);

    assertEquals("simple", flowProcess.getFlowName());
    assertEquals("测试简单流程", flowProcess.getDisplayName());

    Map<String, Object> args = new HashMap<String, Object>();
    args.put("task1.operator", new String[] { "1" });
    FlowOrder order = flowEngine.startInstanceByName("simple", null, "2", args);
    System.out.println("order=" + order);
    List<FlowTask> tasks = flowEngine.flowQueryService().getActiveTasks(String.valueOf(order.getId()));
    for (FlowTask task : tasks) {
      flowEngine.executeTask(task.getId().toString(), "1");
    }
  }

}
