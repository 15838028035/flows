package com.thinkit.cloud.flows;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import com.thinkit.cloud.flows.service.ConfigurationService;
import com.thinkit.cloud.flows.service.FlowEngine;

/**
 * 
 * spring上下文
 *
 */
@Configuration
public class SpringContextHolderExt extends SpringContextHolder implements InitializingBean {

  /**
   * 重写
   */
  public void afterPropertiesSet() throws Exception {

    ConfigurationService configurationService = (ConfigurationService) super.getBean("configurationService");
    FlowEngine flowEngine = (FlowEngine) super.getBean("flowEngine");
    System.out.println("Spring ApplicationContextAware afterPropertiesSet");
    flowEngine = configurationService.buildSnakerEngine();
  }
}
