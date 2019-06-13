package com.thinkit.cloud.flows.parser.impl;

import org.w3c.dom.Element;

import com.thinkit.cloud.flows.model.CustomModel;
import com.thinkit.cloud.flows.model.NodeModel;
import com.thinkit.cloud.flows.parser.AbstractNodeParser;

/**
 * 自定义节点解析器
 */
public class CustomParser extends AbstractNodeParser {
  protected void parseNode(NodeModel node, Element element) {
    CustomModel custom = (CustomModel) node;
    custom.setClazz(element.getAttribute(ATTR_CLAZZ));
    custom.setMethodName(element.getAttribute(ATTR_METHODNAME));
    custom.setArgs(element.getAttribute(ATTR_ARGS));
    custom.setVar(element.getAttribute(ATTR_VAR));
  }

  protected NodeModel newModel() {
    return new CustomModel();
  }

}