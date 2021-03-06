package com.thinkit.cloud.flows.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.bean.FlowTaskActor;

/**
 * 
 * 任务参与者测试
 *
 */
public class FlowTaskActorServiceImplTest extends FlowBaseTest {

  @Test
  public void removeTaskActorTest() throws Exception {
    List<FlowTaskActor> list = flowEngine.flowTaskActorService().getTaskActorsByTaskId(null);
    assertNotNull(list);
    assertTrue(list.size() > 0);

    FlowTaskActor flowTaskActor = list.get(0);

    flowEngine.flowTaskActorService().removeTaskActor(String.valueOf(flowTaskActor.getTaskId()), flowTaskActor.getActorId());

    list = flowEngine.flowTaskActorService().getTaskActorsByTaskId(flowTaskActor.getId().toString());
    assertTrue(list == null || list.size() == 0);
  }

}
