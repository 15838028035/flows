package com.thinkit.cloud.flows.subprocess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.util.FileUtil;

/**
 * 
 * 子流程测试
 *
 */
public class SubProcessTask1Test extends FlowBaseTest {

  @Before
  public void before() {
    flowEngine.flowProcessService()
        .deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/subprocess/childFlow.xml"));
    processId = flowEngine.flowProcessService()
        .deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/subprocess/flow1.xml"));

  }

  @Test
  public void taskTest() throws Exception {
    Map<String, Object> args = new HashMap<String, Object>();
    args.put("task1.operator", new String[] { "1" });
    FlowOrder order = flowEngine.startInstanceById(processId, "2", args);
    System.out.println("************************" + order);

    List<FlowTask> tasks = flowEngine.flowQueryService().getActiveTasks(String.valueOf(order.getId()));
    for (FlowTask task : tasks) {
      System.out.println("************************begin:::::" + task);
      flowEngine.executeTask(task.getId().toString(), "1", args);
      System.out.println("************************end:::::" + task);
    }
  }
}
