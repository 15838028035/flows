package com.thinkit.cloud.flows;

import java.util.Random;

import com.thinkit.cloud.flows.model.ProcessModel;
import com.zhongkexinli.micro.serv.common.util.DateUtil;

/**
 * 默认的流程实例编号生成器 编号生成规则为:yyyyMMddHHmmss-random
 */
public class DefaultNoGenerator implements INoGenerator {
  /**
   * 生成
   */
  public String generate(ProcessModel model) {
    // 一定要确保流程编号的唯一性
    String time = DateUtil.getNowDate("yyyyMMdd HHmmss").replace(" ", "");
    Random random = new Random();
    return time + "-" + random.nextInt(1000);
  }
}