package com.thinkit.cloud.flows.task.field;

import org.junit.Before;
import org.junit.Test;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.bean.FlowProcess;
import com.thinkit.cloud.flows.model.ProcessModel;
import com.thinkit.cloud.flows.model.TaskModel;
import com.thinkit.cloud.flows.util.FileUtil;

/**
 * 
 * 字段配置
 *
 */
public class FieldTest extends FlowBaseTest {
  @Before
  public void before() {
    processId = flowEngine.flowProcessService()
        .deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/field/flow1.xml"));
  }

  @Test
  public void taskTest() throws Exception {
    ProcessModel model = ((FlowProcess) (flowEngine.flowProcessService().getProcessById(processId))).getModel();
    TaskModel taskModel = (TaskModel) model.getNode("task1");
    System.out.println(taskModel.getFields());
  }

}
