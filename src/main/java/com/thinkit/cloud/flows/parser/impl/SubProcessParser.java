package com.thinkit.cloud.flows.parser.impl;

import org.apache.commons.lang.math.NumberUtils;
import org.w3c.dom.Element;

import com.thinkit.cloud.flows.model.NodeModel;
import com.thinkit.cloud.flows.model.SubProcessModel;
import com.thinkit.cloud.flows.parser.AbstractNodeParser;
import com.thinkit.cloud.flows.util.ConfigHelper;
import com.zhongkexinli.micro.serv.common.util.StringUtil;

/**
 * 
 * 子流程解析
 *
 */
public class SubProcessParser extends AbstractNodeParser {
  /**
   * 产生SubProcessModel模型对象
   */
  protected NodeModel newModel() {
    return new SubProcessModel();
  }

  /**
   * 解析decisition节点的特有属性expr
   */
  protected void parseNode(NodeModel node, Element element) {
    SubProcessModel model = (SubProcessModel) node;
    model.setProcessName(element.getAttribute(ATTR_PROCESSNAME));
    String version = element.getAttribute(ATTR_VERSION);
    Long ver = 0L;
    if (NumberUtils.isNumber(version)) {
      ver = Long.parseLong(version);
    }
    model.setVersion(ver);
    String form = element.getAttribute(ATTR_FORM);
    if (StringUtil.isNotBlank(form)) {
      model.setForm(form);
    } else {
      model.setForm(ConfigHelper.getProperty("subprocessurl"));
    }
  }
}
