package com.thinkit.cloud.flows.parser.impl;

import com.thinkit.cloud.flows.model.ForkModel;
import com.thinkit.cloud.flows.model.NodeModel;
import com.thinkit.cloud.flows.parser.AbstractNodeParser;

/**
 * 分支节点解析类
 */
public class ForkParser extends AbstractNodeParser {
  /**
   * 产生ForkModel模型对象
   */
  protected NodeModel newModel() {
    return new ForkModel();
  }
}