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

/**
 * 
 * 借款测试
 *
 */
public class FlowBorrowTest extends FlowBaseTest {
  @Before
  public void before() throws Exception {
    processId = "126";
  }

  @Test
  public void taskTest() throws Exception {
    FlowProcess flowProcess = (FlowProcess) flowEngine.flowProcessService().getProcessById(processId);

    assertEquals("借款测试流程", flowProcess.getFlowName());
    assertEquals("借款测试流程", flowProcess.getDisplayName());

    Map<String, Object> args = new HashMap<String, Object>();
    FlowOrder order = flowEngine.startInstanceByName(flowProcess.getFlowName(), null, "null", args);
    System.out.println("order=" + order);
    List<FlowTask> tasks = flowEngine.flowQueryService().getActiveTasks(String.valueOf(order.getId()));
    for (FlowTask task : tasks) {
      // flowEngine.executeTask(task.getId().toString(), "1");
    }

    System.out.println("tasksSize:" + tasks.size());
  }

}
