package com.thinkit.cloud.flows.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.bean.FlowTaskActor;

/**
 * 
 * 自定义访问测试
 *
 */
public class GeneralAccessStrategyServiceImplTest extends FlowBaseTest {

  /**
   * 是否允许执行任务测试
   * 
   * @throws Exception 异常
   */
  @Test
  public void isAllowedTest() throws Exception {
    List<FlowTaskActor> actors = new ArrayList<FlowTaskActor>();
    FlowTaskActor flowTaskActor1 = new FlowTaskActor();
    flowTaskActor1.setActorId("actor1");

    FlowTaskActor flowTaskActor2 = new FlowTaskActor();
    flowTaskActor2.setActorId("actor1");

    actors.add(flowTaskActor1);
    actors.add(flowTaskActor2);
    assertTrue(flowEngine.flowTaskAccessStrategyService().isAllowed("actor1", actors));
    assertTrue(flowEngine.flowTaskAccessStrategyService().isAllowed("actor2", actors));
    assertFalse(flowEngine.flowTaskAccessStrategyService().isAllowed("actorNotFound", actors));
  }

}
