package com.thinkit.cloud.flows.parser.impl;

import com.thinkit.cloud.flows.model.EndModel;
import com.thinkit.cloud.flows.model.NodeModel;
import com.thinkit.cloud.flows.parser.AbstractNodeParser;

/**
 * 结束节点解析类
 */
public class EndParser extends AbstractNodeParser {
  /**
   * 产生EndModel模型对象
   */
  protected NodeModel newModel() {
    return new EndModel();
  }
}