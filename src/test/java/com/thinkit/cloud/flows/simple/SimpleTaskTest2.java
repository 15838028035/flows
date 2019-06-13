package com.thinkit.cloud.flows.simple;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowProcess;
import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.bean.FlowTaskHist;
import com.thinkit.cloud.flows.model.ProcessModel;
import com.thinkit.cloud.flows.util.Assert;
import com.thinkit.cloud.flows.util.FileUtil;
import com.thinkit.cloud.flows.util.FlowUtil;
import com.thinkit.cloud.flows.util.JsonUtil;
import com.zhongkexinli.micro.serv.common.util.StringUtil;

/**
 * 
 * 简单流程测试
 *
 */
public class SimpleTaskTest2 extends FlowBaseTest {

  @Test
  public void taskTest() throws Exception {
    FlowProcess flowProcess = flowEngine.flowProcessService().getProcessById(processId);
    assertNotNull(flowProcess);
  }

  @Test
  public void taskDeployTest() throws Exception {
    InputStream input = FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/simple/11.xml");
    flowEngine.flowProcessService().redeploy(processId, input);
  }

  @Test
  public void diagramJsonTest() throws Exception {
    processId = flowEngine.flowProcessService()
        .deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/simple/leaveTest.xml"));

    FlowProcess process = flowEngine.flowProcessService().getProcessById(processId);
    Assert.notNull(process);

    Map<String, Object> args = new HashMap<String, Object>();
    args.put("task1.operator", new String[] { "admin管理员" });
    FlowOrder order = flowEngine.startInstanceByName("simple", null, "2", args);
    System.out.println("order=" + order);

    String orderId = order.getId().toString();

    ProcessModel model = process.getModel();
    Map<String, String> jsonMap = new HashMap<String, String>();
    if (model != null) {
      jsonMap.put("process", FlowUtil.getModelJson(model));
    }

    if (StringUtil.isNotBlank(orderId)) {
      List<FlowTask> tasks = flowEngine.flowQueryService().getActiveTasks(orderId);

      List<FlowTaskHist> historyTasks = flowEngine.flowQueryService().getHistoryTasks(orderId);
      jsonMap.put("state", FlowUtil.getStateJson(model, tasks, historyTasks));

    }
    jsonMap.get("state");

    String jsonMapStr = JsonUtil.toJson(jsonMap);

    System.out.println("jsonMapStr=" + jsonMapStr);
  }
}
