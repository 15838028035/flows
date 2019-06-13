package com.thinkit.cloud.flows.model;

import com.thinkit.cloud.flows.DecisionHandler;
import com.thinkit.cloud.flows.Expression;
import com.thinkit.cloud.flows.core.Execution;
import com.thinkit.cloud.flows.core.ServiceContext;
import com.thinkit.cloud.flows.exceptions.FlowException;
import com.thinkit.cloud.flows.util.ClassUtil;
import com.zhongkexinli.micro.serv.common.util.StringUtil;

/**
 * 决策定义decision元素
 */
public class DecisionModel extends NodeModel {
  /**
   * 
   */
  private static final long serialVersionUID = -806621814645169999L;
  /**
   * 决策选择表达式串（需要表达式引擎解析）
   */
  private String expr;
  /**
   * 决策处理类，对于复杂的分支条件，可通过handleClass来处理
   */
  private String handleClass;
  /**
   * 决策处理类实例
   */
  private DecisionHandler decide;
  /**
   * 表达式解析器
   */
  private transient Expression expression;

  /**
   * 执行
   */
  public void exec(Execution execution) {
    LOG.info(execution.getFlowOrder().getId() + "->decision execution.getArgs():" + execution.getArgs());
    if (expression == null) {
      expression = ServiceContext.getContext().find(Expression.class);
    }
    LOG.info("expression is " + expression);
    if (expression == null) {
      throw new FlowException("表达式解析器为空，请检查配置.");
    }
    String next = null;
    if (StringUtil.isNotBlank(expr)) {
      next = expression.eval(String.class, expr, execution.getArgs());
    } else if (decide != null) {
      next = decide.decide(execution);
    }
    LOG.info(execution.getFlowOrder().getId() + "->decision expression[expr=" + expr + "] return result:" + next);
    boolean isfound = false;
    for (TransitionModel tm : getOutputs()) {
      if (StringUtil.isBlank(next)) {
        String expr = tm.getExpr();
        if (StringUtil.isNotBlank(expr) && expression.eval(Boolean.class, expr, execution.getArgs())) {
          tm.setEnabled(true);
          tm.execute(execution);
          isfound = true;
        }
      } else {
        if (tm.getName().equals(next)) {
          tm.setEnabled(true);
          tm.execute(execution);
          isfound = true;
        }
      }
    }
    if (!isfound) {
      throw new FlowException(execution.getFlowOrder().getId() + "->decision节点无法确定下一步执行路线");
    }
  }

  public String getExpr() {
    return expr;
  }

  public void setExpr(String expr) {
    this.expr = expr;
  }

  public String getHandleClass() {
    return handleClass;
  }

  /**
   * 设置处理类
   * 
   * @param handleClass
   *          处理类
   */
  public void setHandleClass(String handleClass) {
    this.handleClass = handleClass;
    if (StringUtil.isNotBlank(handleClass)) {
      decide = (DecisionHandler) ClassUtil.newInstance(handleClass);
    }
  }
}