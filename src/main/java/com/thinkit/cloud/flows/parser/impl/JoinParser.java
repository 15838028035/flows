package com.thinkit.cloud.flows.parser.impl;

import com.thinkit.cloud.flows.model.JoinModel;
import com.thinkit.cloud.flows.model.NodeModel;
import com.thinkit.cloud.flows.parser.AbstractNodeParser;

/**
 * 合并节点解析类
 */
public class JoinParser extends AbstractNodeParser {
  /**
   * 产生JoinModel模型对象
   */
  protected NodeModel newModel() {
    return new JoinModel();
  }
}