package com.thinkit.cloud.flows.core;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thinkit.cloud.flows.Context;
import com.thinkit.cloud.flows.service.FlowEngine;
import com.thinkit.cloud.flows.util.Assert;

/**
 * 单实例的服务上下文 具体的上下文查找服务交给Context的实现类
 */
public abstract class ServiceContext {
  private static Log log = LogFactory.getLog(ServiceContext.class);

  /**
   * 上下文接口服务{@link Context}
   */
  private static Context context;

  /**
   * 流程引擎的引用
   */
  private static FlowEngine engine;

  /**
   * 获取Context实现类
   * 
   * @return 获取Context实现类
   */
  public static Context getContext() {
    return context;
  }

  /**
   * 设置Context实现类
   * 
   * @param context 设置Context实现类
   */
  public static void setContext(Context context) {
    ServiceContext.context = context;
  }

  /**
   * 获取注册的引擎实例
   * 
   * @return 获取注册的引擎实例
   */
  public static FlowEngine getEngine() {
    Assert.notNull(context, "未注册服务上下文");
    if (engine == null) {
      engine = context.find(FlowEngine.class);
    }
    return engine;
  }

  /**
   * 向上下文添加服务实例
   * 
   * @param name
   *          服务名称
   * @param object
   *          服务实例
   */
  public static void put(String name, Object object) {
    Assert.notNull(context, "未注册服务上下文");
    if (log.isInfoEnabled()) {
      log.info("put new instance[name=" + name + "][object=" + object + "]");
    }
    context.put(name, object);
  }

  /**
   * 向上下文添加服务实例
   * 
   * @param name
   *          服务名称
   * @param clazz
   *          服务类型
   */
  public static void put(String name, Class<?> clazz) {
    Assert.notNull(context, "未注册服务上下文");
    if (log.isInfoEnabled()) {
      log.info("put new instance[name=" + name + "][clazz=" + clazz.getName() + "]");
    }
    context.put(name, clazz);
  }

  /**
   * 根据服务名称判断是否存在服务实例
   * 
   * @param name
   *          服务名称
   * @return 是否
   */
  public static boolean exist(String name) {
    Assert.notNull(context, "未注册服务上下文");
    return context.exist(name);
  }

  /**
   * 对外部提供的查找对象方法，根据class类型查找
   * 
   * @param clazz
   *          服务类型
   * @return 根据class类型查找
   */
  public static <T> T find(Class<T> clazz) {
    Assert.notNull(context, "未注册服务上下文");
    return context.find(clazz);
  }

  /**
   * 对外部提供的查找对象实例列表方法，根据class类型查找集合
   * 
   * @param clazz
   *          服务类型
   * @return 根据class类型查找集合
   */
  public static <T> List<T> findList(Class<T> clazz) {
    Assert.notNull(context, "未注册服务上下文");
    return context.findList(clazz);
  }

  /**
   * 对外部提供的查找对象方法，根据名称、class类型查找
   * 
   * @param name
   *          服务名称
   * @param clazz
   *          服务类型
   * @return 对外部提供的查找对象方法
   */
  public static <T> T findByName(String name, Class<T> clazz) {
    Assert.notNull(context, "未注册服务上下文");
    return context.findByName(name, clazz);
  }
}