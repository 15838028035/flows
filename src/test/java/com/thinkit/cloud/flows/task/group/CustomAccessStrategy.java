package com.thinkit.cloud.flows.task.group;

import java.util.ArrayList;
import java.util.List;

import com.thinkit.cloud.flows.service.impl.GeneralAccessStrategyServiceImpl;

/**
 * 基于用户或组（角色、部门等）的访问策略类 该策略类适合组作为参与者的情况
 */
public class CustomAccessStrategy extends GeneralAccessStrategyServiceImpl {

  @Override
  protected List<String> ensureGroup(String operator) {
    List<String> groups = new ArrayList<String>();
    if (operator.equals("test")) {
      groups.add("test");
    } else {
      groups.add("role1");
    }
    return groups;
  }
}
