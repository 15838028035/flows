package com.thinkit.cloud.flows.task.group;

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
 * 基于用户或组（角色、部门等）的访问策略类 该策略类适合组作为参与者的情况
 */
public class GroupTaskTest extends FlowBaseTest {
  @Before
  public void before() {
    processId = flowEngine.flowProcessService()
        .deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/group/flow1.xml"));
  }

  @Test
  public void taskTest() throws Exception {
    Map<String, Object> args = new HashMap<String, Object>();
    args.put("task1.operator", new String[] { "role1" });

    FlowOrder order = flowEngine.startInstanceByName("group", null, "2", args);
    System.out.println("order=" + order);
    List<FlowTask> tasks = flowEngine.flowQueryService().getActiveTasks(String.valueOf(order.getId()));
    for (FlowTask task : tasks) {
      flowEngine.executeTask(task.getId().toString(), "test1", args);
    }
  }

}
