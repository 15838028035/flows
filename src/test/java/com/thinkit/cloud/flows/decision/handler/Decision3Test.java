package com.thinkit.cloud.flows.decision.handler;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowProcess;
import com.thinkit.cloud.flows.util.FileUtil;
import com.thinkit.cloud.flows.util.JsonUtil;

/**
 * 分子测试
 * 
 */
public class Decision3Test extends FlowBaseTest {
  @Before
  public void before() {
    processId = flowEngine.flowProcessService()
        .deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/decision/handler/flow1.xml"));
  }

  @Test
  public void taskTest() throws Exception {
    FlowProcess flowProcess = (FlowProcess) flowEngine.flowProcessService().getProcessById(processId);

    assertEquals("decision3", flowProcess.getFlowName());
    assertEquals("测试分支流程3", flowProcess.getDisplayName());

    Map<String, Object> args = new HashMap<String, Object>();
    args.put("task1.operator", new String[] { "1" });
    args.put("task2.operator", new String[] { "1" });
    args.put("task3.operator", new String[] { "1" });
    args.put("content", "toTask3");
    FlowOrder order = flowEngine.startInstanceById(processId, "2", args);

    String mapJson = JsonUtil.toJson(args);
    System.out.println(order.getVariable());
  }

}
