package com.thinkit.cloud.flows.parser.impl;

import org.w3c.dom.Element;

import com.thinkit.cloud.flows.model.DecisionModel;
import com.thinkit.cloud.flows.model.NodeModel;
import com.thinkit.cloud.flows.parser.AbstractNodeParser;

/**
 * 决策节点解析类
 */
public class DecisionParser extends AbstractNodeParser {
  /**
   * 产生DecisionModel模型对象
   */
  protected NodeModel newModel() {
    return new DecisionModel();
  }

  /**
   * 解析decisition节点的特有属性expr
   */
  protected void parseNode(NodeModel node, Element element) {
    DecisionModel decision = (DecisionModel) node;
    decision.setExpr(element.getAttribute(ATTR_EXPR));
    decision.setHandleClass(element.getAttribute(ATTR_HANDLECLASS));
  }
}