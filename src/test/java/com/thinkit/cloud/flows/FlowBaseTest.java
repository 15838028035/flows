package com.thinkit.cloud.flows;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkit.cloud.flows.service.FlowEngine;
import com.thinkit.cloud.flows.service.impl.FlowEngineFacetsServiceImpl;

/**
 * 
 * 流程测试基类
 *
 */
@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration(locations = { "classpath:spring-common-test.xml" })
public class FlowBaseTest {
  protected String processId;
  @Autowired
  protected FlowEngineFacetsServiceImpl flowEngineFacets;

  @Autowired
  protected FlowEngine flowEngine;

  @BeforeClass
  public static void setUpRunEnvNew() {
    System.setProperty("spring.profiles.active", "dev");
  }

}
