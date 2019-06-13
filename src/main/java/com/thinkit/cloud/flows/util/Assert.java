package com.thinkit.cloud.flows.util;

import com.zhongkexinli.micro.serv.common.util.StringUtil;

/**
 * 
 * 断言工具类
 *
 */
public abstract class Assert {
  
  /**
   * 断言是否为真
   * @param expression  断言对象
   * @param message 异常消息
   */
  public static void isTrue(boolean expression, String message) {
    if (!expression) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void isTrue(boolean expression) {
    isTrue(expression, "[Assertion failed] - this expression must be true");
  }

  /**
   * 是否为空
   * @param object 断言对象
   * @param message 异常消息
   */
  public static void isNull(Object object, String message) {
    if (object != null) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void isNull(Object object) {
    isNull(object, "[Assertion failed] - the object argument must be null");
  }

  /**
   * 是否不为空
   * @param object 断言对象
   * @param message 异常消息
   */
  public static void notNull(Object object, String message) {
    if (object == null) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void notNull(Object object) {
    notNull(object, "[Assertion failed] - this argument is required; it must not be null");
  }

  /**
   * 是否有长度
   * @param text 断言对象
   * @param message 异常消息
   */
  public static void hasLength(String text, String message) {
    if (!StringUtil.hasLength(text))  {
      throw new IllegalArgumentException(message);
    }
  }

  public static void hasLength(String text) {
    hasLength(text, "[Assertion failed] - this String argument must have length; it must not be null or empty");
  }

  /**
   * 是否包含文本
   * @param text 断言对象
   * @param message 异常消息
   */
  public static void hasText(String text, String message) {
    if (StringUtil.isBlank(text))  {
      throw new IllegalArgumentException(message);
    }
  }

  public static void hasText(String text) {
    hasText(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
  }

  public static void isAssignable(Class superType, Class subType) {
    isAssignable(superType, subType, "");
  }

  public static void isAssignable(Class superType, Class subType, String message) {
    notNull(superType, "Type to check against must not be null");
    if ((subType == null) || (!superType.isAssignableFrom(subType)))
      throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
  }
}