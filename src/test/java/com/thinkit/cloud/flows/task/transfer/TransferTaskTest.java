package com.thinkit.cloud.flows.task.transfer;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.util.FileUtil;

/**
 * 
 *转派测试
 *
 */
public class TransferTaskTest extends FlowBaseTest {
  @Before
  public void before() {
    processId = flowEngine.flowProcessService()
        .deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/transfer/flow1.xml"));
  }

  @Test
  public void taskTest() throws Exception {
    FlowOrder order = flowEngine.startInstanceByName("transfer", 0L);
    System.out.println("order=" + order);
    List<FlowTask> tasks = flowEngine.flowQueryService().getActiveTasks(String.valueOf(order.getId()));
    for (FlowTask task : tasks) {
      flowEngine.flowTaskServiceApi().createNewTask(String.valueOf(task.getId()), "0", "test");
      flowEngine.flowTaskServiceApi().complete(task.getId().toString());
    }
  }

}
