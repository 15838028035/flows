package com.thinkit.cloud.flows.parser.impl;

import com.thinkit.cloud.flows.model.NodeModel;
import com.thinkit.cloud.flows.model.StartModel;
import com.thinkit.cloud.flows.parser.AbstractNodeParser;

/**
 * 
 * 开始节点
 *
 */
public class StartParser extends AbstractNodeParser {
  /**
   * 产生StartModel模型对象
   */
  protected NodeModel newModel() {
    return new StartModel();
  }

}