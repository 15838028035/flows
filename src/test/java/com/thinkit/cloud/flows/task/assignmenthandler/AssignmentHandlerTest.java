package com.thinkit.cloud.flows.task.assignmenthandler;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.util.FileUtil;

/**
 * 
 * 分配处理者测试
 *
 */
public class AssignmentHandlerTest extends FlowBaseTest {
  @Before
  public void before() {
    processId = flowEngine.flowProcessService()
        .deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/assignmenthandler/flow1.xml"));
  }

  @Test
  public void taskTest() throws Exception {
    FlowOrder order = flowEngine.startInstanceByName("assignmenthandler");
    System.out.println("order=" + order);
    List<FlowTask> tasks = flowEngine.flowQueryService().getActiveTasks(String.valueOf(order.getId()));
    for (FlowTask task : tasks) {
      flowEngine.executeTask(task.getId().toString(), "admin");
    }
  }

}
