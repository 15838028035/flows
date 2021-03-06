package com.thinkit.cloud.flows.util;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.bean.FlowTaskHist;
import com.thinkit.cloud.flows.model.CustomModel;
import com.thinkit.cloud.flows.model.DecisionModel;
import com.thinkit.cloud.flows.model.EndModel;
import com.thinkit.cloud.flows.model.ExtTaskModel;
import com.thinkit.cloud.flows.model.ForkModel;
import com.thinkit.cloud.flows.model.JoinModel;
import com.thinkit.cloud.flows.model.NodeModel;
import com.thinkit.cloud.flows.model.ProcessModel;
import com.thinkit.cloud.flows.model.StartModel;
import com.thinkit.cloud.flows.model.SubProcessModel;
import com.thinkit.cloud.flows.model.TaskModel;
import com.thinkit.cloud.flows.model.TransitionModel;
import com.zhongkexinli.micro.serv.common.util.StringUtil;

/**
 * 
 * 流程工具类
 *
 */
public class FlowUtil {

  private static Log logger = LogFactory.getLog(FlowUtil.class);
  
  private static Map<Class<? extends NodeModel>, String> mapper = new HashMap<Class<? extends NodeModel>, String>();
  static {
    mapper.put(ExtTaskModel.class, "task");
    mapper.put(CustomModel.class, "custom");
    mapper.put(DecisionModel.class, "decision");
    mapper.put(EndModel.class, "end");
    mapper.put(ForkModel.class, "fork");
    mapper.put(JoinModel.class, "join");
    mapper.put(StartModel.class, "start");
    mapper.put(SubProcessModel.class, "subprocess");
  }

  /**
   * 获得状态json
   * @param model 流程模型
   * @param activeFlowTasks 活动任务
   * @param historyFlowTasks 历史任务
   * @return 获得状态json
   */
  public static String getStateJson(ProcessModel model, List<FlowTask> activeFlowTasks,
      List<FlowTaskHist> historyFlowTasks) {
    StringBuilder buffer = new StringBuilder();
    buffer.append("{'activeRects':{'rects':[");
    if (activeFlowTasks != null && activeFlowTasks.size() > 0) {
      for (FlowTask flowTask : activeFlowTasks) {
        buffer.append("{'paths':[],'name':'");
        buffer.append(flowTask.getTaskName());
        buffer.append("'},");
      }
      buffer.deleteCharAt(buffer.length() - 1);
    }
    buffer.append("]}, 'historyRects':{'rects':[");
    if (historyFlowTasks != null && historyFlowTasks.size() > 0) {
      for (FlowTaskHist historyFlowTask : historyFlowTasks) {
        NodeModel parentModel = model.getNode(historyFlowTask.getTaskName());
        if (parentModel == null)  {
          continue;
        }
        buffer.append("{'name':'").append(parentModel.getName()).append("','paths':[");
        buffer.append("]},");
      }
      buffer.deleteCharAt(buffer.length() - 1);
    }
    buffer.append("]}}");
    return buffer.toString();
  }

  /**
   * 获得模型json
   * @param model 流程模型
   * @return 模型json
   */
  public static String getModelJson(ProcessModel model) {
    StringBuilder buffer = new StringBuilder();
    List<TransitionModel> tms = new ArrayList<TransitionModel>();
    for (NodeModel node : model.getNodes()) {
      for (TransitionModel tm : node.getOutputs()) {
        tms.add(tm);
      }
    }
    buffer.append("{");
    buffer.append(getNodeJson(model.getNodes()));
    buffer.append(getPathJson(tms));
    buffer.append("props:{props:{name:{name:'name',value:'");
    buffer.append(convert(model.getName()));
    buffer.append("'},displayName:{name:'displayName',value:'");
    buffer.append(convert(model.getDisplayName()));
    buffer.append("'},expireTime:{name:'expireTime',value:'");
    buffer.append(convert(model.getExpireTime()));
    buffer.append("'},instanceUrl:{name:'instanceUrl',value:'");
    buffer.append(convert(model.getInstanceUrl()));
    buffer.append("'},instanceNoClass:{name:'instanceNoClass',value:'");
    buffer.append(convert(model.getInstanceNoClass()));
    buffer.append("'}}}}");
    return buffer.toString();
  }

  /**
   * 获得流程json
   * @param nodes 流程节点
   * @return json字符串
   */
  public static String getNodeJson(List<NodeModel> nodes) {
    StringBuilder buffer = new StringBuilder();
    buffer.append("states: {");
    for (NodeModel node : nodes) {
      buffer.append(node.getName());
      buffer.append(getBase(node));
      buffer.append(getLayout(node));
      buffer.append(getProperty(node));
      buffer.append(",");
    }
    buffer.deleteCharAt(buffer.length() - 1);
    buffer.append("},");
    return buffer.toString();
  }

  /**
   * 获得路径
   * @param tms  TransitionModel
   * @return 路径
   */
  public static String getPathJson(List<TransitionModel> tms) {
    StringBuilder buffer = new StringBuilder();
    buffer.append("paths:{");
    for (TransitionModel tm : tms) {
      buffer.append(tm.getName());
      buffer.append(":{from:'");
      buffer.append(tm.getSource().getName());
      buffer.append("',to:'");
      buffer.append(tm.getTarget().getName());
      buffer.append("', dots:[");
      if (StringUtil.isNotBlank(tm.getG())) {
        String[] bendpoints = tm.getG().split(";");
        for (String bendpoint : bendpoints) {
          buffer.append("{");
          String[] xy = bendpoint.split(",");
          buffer.append("x:").append(getNumber(xy[0]));
          buffer.append(",y:").append(xy[1]);
          buffer.append("},");
        }
        buffer.deleteCharAt(buffer.length() - 1);
      }
      buffer.append("],text:{text:'");
      buffer.append(tm.getDisplayName());
      buffer.append("'},textPos:{");
      if (StringUtil.isNotBlank(tm.getOffset())) {
        String[] values = tm.getOffset().split(",");
        buffer.append("x:").append(values[0]).append(",");
        buffer.append("y:").append(values[1]).append("");
      }
      buffer.append("}, props:{name:{value:'" + tm.getName() + "'},expr:{value:'" + tm.getExpr() + "'}}}");
      buffer.append(",");
    }
    buffer.deleteCharAt(buffer.length() - 1);
    buffer.append("},");
    return buffer.toString();
  }

  /**
   * 获得base信息
   * @param node 节点模型
   * @return base信息
   */
  public static String getBase(NodeModel node) {
    StringBuilder buffer = new StringBuilder();
    buffer.append(":{type:'");
    buffer.append(mapper.get(node.getClass()));
    buffer.append("',text:{text:'");
    buffer.append(node.getDisplayName());
    buffer.append("'},");
    return buffer.toString();
  }

  private static String getProperty(NodeModel node) {
    StringBuilder buffer = new StringBuilder();
    buffer.append("props:{");
    try {
      PropertyDescriptor[] beanProperties = PropertyUtils.getPropertyDescriptors(node);
      for (PropertyDescriptor propertyDescriptor : beanProperties) {
        if (propertyDescriptor.getReadMethod() == null || propertyDescriptor.getWriteMethod() == null) {
          continue;
        }
        String name = propertyDescriptor.getName();
        String value = "";
        if (propertyDescriptor.getPropertyType() == String.class) {
          value = (String) BeanUtils.getProperty(node, name);
        } else {
          continue;
        }
        if (value == null || value.equals(""))  {
          continue;
        }
        buffer.append(name);
        buffer.append(":{value:'");
        buffer.append(convert(value));
        buffer.append("'},");
      }
    } catch (Exception e) {
      logger.error(e);
    }
    buffer.deleteCharAt(buffer.length() - 1);
    buffer.append("}}");
    return buffer.toString();
  }

  private static String getLayout(NodeModel node) {
    StringBuilder buffer = new StringBuilder();
    buffer.append("attr:{");
    String[] values = node.getLayout().split(",");
    buffer.append("x:").append(getNumber(values[0])).append(",");
    buffer.append("y:").append(values[1]).append(",");
    if ("-1".equals(values[2])) {
      if (node instanceof TaskModel || node instanceof CustomModel || node instanceof SubProcessModel) {
        values[2] = "100";
      } else {
        values[2] = "50";
      }
    }
    if ("-1".equals(values[3])) {
      values[3] = "50";
    }
    buffer.append("width:").append(values[2]).append(",");
    buffer.append("height:").append(values[3]);
    buffer.append("},");
    return buffer.toString();
  }

  /**
   * 转换
   * @param value 值
   * @return 转换结果 
   */
  private static String convert(String value) {
    if (StringUtils.isEmpty(value)) {
      return "";
    }
    if (value.indexOf("'") != -1) {
      value = value.replaceAll("'", "#1");
    }
    if (value.indexOf("\"") != -1) {
      value = value.replaceAll("\"", "#2");
    }
    if (value.indexOf("\r\n") != -1) {
      value = value.replaceAll("\r\n", "#3");
    }
    if (value.indexOf("\n") != -1) {
      value = value.replaceAll("\n", "#4");
    }
    if (value.indexOf(">") != -1) {
      value = value.replaceAll(">", "#5");
    }
    if (value.indexOf("<") != -1) {
      value = value.replaceAll("<", "#6");
    }
    if (value.indexOf("&amp;") != -1) {
      value = value.replaceAll("&amp;", "#7");
    }
    return value;
  }

  /**
   * 转换xml
   * @param value 值
   * @return 转换xml
   */
  public static String convertXml(String value) {
    if (value.indexOf("#1") != -1) {
      value = value.replaceAll("#1", "'");
    }
    if (value.indexOf("#2") != -1) {
      value = value.replaceAll("#2", "\"");
    }
    if (value.indexOf("#5") != -1) {
      value = value.replaceAll("#5", "&gt;");
    }
    if (value.indexOf("#6") != -1) {
      value = value.replaceAll("#6", "&lt;");
    }
    if (value.indexOf("&") != -1) {
      value = value.replaceAll("#7", "&amp;");
    }
    return value;
  }

  private static int getNumber(String value) {
    if (value == null)  {
      return 0;
    }
    try {
      return Integer.parseInt(value) + 180;
    } catch (Exception e) {
      return 0;
    }
  }
}
