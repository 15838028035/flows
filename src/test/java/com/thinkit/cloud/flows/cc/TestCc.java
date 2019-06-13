package com.thinkit.cloud.flows.cc;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.bean.FlowOrder;
import com.zhongkexinli.micro.serv.common.util.FileUtil;

/**
 * 
 * 抄送测试
 *
 */
public class TestCc extends FlowBaseTest {
  @Before
  public void before() {
    processId = flowEngine.flowProcessService()
        .deploy(FileUtil.getResourceAsStream("com/lj/app/core/common/flows/task/simple/flow1.xml"));
  }

  @Test
  public void flowTest() throws Exception {
    Map<String, Object> args = new HashMap<String, Object>();
    args.put("task1.operator", new String[] { "1" });
    FlowOrder order = flowEngineFacets.startInstanceByName("simple", 0L, "2", args);
    flowEngine.flowCcOrderService().createCcOrder(order.getId().toString(), "test");
  }
}