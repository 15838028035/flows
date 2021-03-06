package com.thinkit.cloud.flows;

import com.thinkit.cloud.flows.model.ProcessModel;

/**
 * 编号生成器接口 流程实例的编号字段使用该接口实现类来产生对应的编号
 */
public interface INoGenerator {
  /**
   * 生成器方法
   * 
   * @param model 流程模型
   * @return String 编号
   */
  String generate(ProcessModel model);
}