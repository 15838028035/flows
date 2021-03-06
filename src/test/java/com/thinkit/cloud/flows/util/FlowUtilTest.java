package com.thinkit.cloud.flows.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.model.ExtTaskModel;
import com.thinkit.cloud.flows.model.NodeModel;
import com.thinkit.cloud.flows.model.TaskModel;

/**
 * 
 * 流程工具类测试
 *
 */
public class FlowUtilTest extends FlowBaseTest {

  /**
   * 流程基础模型获取测试
   * 
   * @throws Exception 异常
   */
  @Test
  public void getBaseTest() throws Exception {
    NodeModel node = new TaskModel();
    String str = FlowUtil.getBase(node);
    System.out.println("str=" + str);
    assertTrue(str.contains("type"));
  }

  /**
   * 自定义任务模型测试
   * 
   * @throws Exception 异常
   */
  @Test
  public void extTaskModelTest() throws Exception {
    NodeModel node = new ExtTaskModel();
    node.setDisplayName("extTaskModel");
    String str = FlowUtil.getBase(node);
    System.out.println("str=" + str);
    assertTrue(str.contains("task"));
  }

}
