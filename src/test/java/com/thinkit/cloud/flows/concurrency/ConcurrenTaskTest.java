package com.thinkit.cloud.flows.concurrency;

import com.thinkit.cloud.flows.FlowBaseTest;
import com.thinkit.cloud.flows.bean.FlowProcess;

/**
 * 
 * 并发测试
 *
 */
public class ConcurrenTaskTest extends FlowBaseTest {

  /**
   * 执行
   * @param a 参数
   * @throws Exception 异常
   */
  public static void main(String[] a) throws Exception {
    ConcurrenTaskTest con = new ConcurrenTaskTest();
    FlowProcess process = con.flowEngine.flowProcessService().getProcessById("1");
    for (int i = 0; i < 50; i++) {
      new Thread(new StartProcess(con.flowEngine, process.getId().toString())).start();
    }
  }
}


