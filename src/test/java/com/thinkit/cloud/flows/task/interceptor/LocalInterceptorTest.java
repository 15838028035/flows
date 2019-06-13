package com.thinkit.cloud.flows.task.interceptor;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowProcess;
import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.util.FileUtil;

/**
 * 
 * 测试局部拦截器
 *
 */
public class LocalInterceptorTest extends FlowBaseTest {
  @Before
  public void before() {
    processId = flowEngine.flowProcessService()
        .deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/interceptor/flow1.xml"));
  }

  @Test
  public void taskTest() throws Exception {
    FlowProcess flowProcess = flowEngine.flowProcessService().getProcessById(processId);

    assertEquals("interceptor", flowProcess.getFlowName());
    assertEquals("测试局部拦截器", flowProcess.getDisplayName());

    FlowOrder order = flowEngine.startInstanceByName(flowProcess.getFlowName());
    System.out.println("order=" + order);
    List<FlowTask> tasks = flowEngine.flowQueryService().getActiveTasks(String.valueOf(order.getId()));
    for (FlowTask task : tasks) {
      flowEngine.executeTask(task.getId().toString());
    }
  }

}
