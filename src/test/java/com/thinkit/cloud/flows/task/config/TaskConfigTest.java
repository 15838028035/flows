package com.thinkit.cloud.flows.task.config;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowProcess;
import com.thinkit.cloud.flows.util.FileUtil;

/**
 * 
 * 任务配置
 *
 */
public class TaskConfigTest extends FlowBaseTest {
  @Before
  public void before() {
    processId = flowEngine.flowProcessService()
        .deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/config/flow1.xml"));
  }

  @Test
  public void taskTest() throws Exception {
    FlowProcess flowProcess = (FlowProcess) flowEngine.flowProcessService().getProcessById(processId);

    assertEquals("config", flowProcess.getFlowName());
    assertEquals("测试预配置参与者", flowProcess.getDisplayName());

    FlowOrder order = flowEngine.startInstanceByName("config", null, "2", null);
    System.out.println("order=" + order);
  }
}
