package com.thinkit.cloud.flows.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thinkit.cloud.flows.exceptions.FlowException;

/**
 * 
 * 反射工具类
 *
 */
public class ReflectUtil {

  private static Log logger = LogFactory.getLog(ReflectUtil.class);
  
  /**
   * 利用反射获取指定对象的指定属性
   * 
   * @param obj
   *          目标对象
   * @param fieldName
   *          目标属性
   * @return 目标属性的值
   */
  public static Object getFieldValue(Object obj, String fieldName) {
    Object result = null;
    Field field = getField(obj, fieldName);
    if (field != null) {
      field.setAccessible(true);
      try {
        result = field.get(obj);
      } catch (IllegalArgumentException e) {
        logger.error(e);
      } catch (IllegalAccessException e) {
        logger.error(e);
      }
    }
    return result;
  }

  /**
   * 利用反射获取指定对象里面的指定属性
   * 
   * @param obj
   *          目标对象
   * @param fieldName
   *          目标属性
   * @return 目标字段
   */
  private static Field getField(Object obj, String fieldName) {
    Field field = null;
    for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
      try {
        field = clazz.getDeclaredField(fieldName);
        break;
      } catch (NoSuchFieldException e) {
        // ignore exception
        logger.error(e);
      }
    }
    return field;
  }

  /**
   * 利用反射设置指定对象的指定属性为指定的值
   * 
   * @param obj
   *          目标对象
   * @param fieldName
   *          目标属性
   * @param fieldValue
   *          目标值
   */
  public static void setFieldValue(Object obj, String fieldName, Object fieldValue) {
    Field field = getField(obj, fieldName);
    if (field != null) {
      try {
        field.setAccessible(true);
        field.set(obj, fieldValue);
      } catch (IllegalArgumentException e) {
        logger.error(e);
      } catch (IllegalAccessException e) {
        logger.error(e);
      }
    }
  }

  /**
   * 根据指定的对象、方法、参数反射调用，并返回调用结果
   * 
   * @param method
   *          方法
   * @param target
   *          对象
   * @param args
   *          参数数组
   * @return 方法调用的返回数据
   */
  public static Object invoke(Method method, Object target, Object[] args) {
    if (method == null) {
      throw new FlowException("方法不能为空");
    }
    try {
      if (!method.isAccessible()) {
        method.setAccessible(true);
      }
      return method.invoke(target, args);
    } catch (InvocationTargetException e) {
      Throwable targetException = e.getTargetException();
      throw new FlowException("不能调用 '" + method.getName() + "' with " + Arrays.toString(args) + " on " + target + ": "
          + targetException.getMessage(), targetException);
    } catch (Exception e) {
      throw new FlowException(
          "不能调用 '" + method.getName() + "' with " + Arrays.toString(args) + " on " + target + ": " + e.getMessage(), e);
    }
  }

  /**
   * 根据class类型、methodName方法名称，返回Method对象。 注意：这里不检查参数类型，所以自定义的java类应该避免使用重载方法
   * 
   * @param clazz 类
   * @param methodName 方法
   * @return Method对象
   */
  public static Method findMethod(Class<?> clazz, String methodName) {
    Method[] candidates = clazz.getDeclaredMethods();
    for (int i = 0; i < candidates.length; i++) {
      Method candidate = candidates[i];
      if (candidate.getName().equals(methodName)) {
        return candidate;
      }
    }
    if (clazz.getSuperclass() != null) {
      return findMethod(clazz.getSuperclass(), methodName);
    }
    return null;
  }

  /**
   * 将反射时的checked exception转换为unchecked exception.
   */
  public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
    if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
        || e instanceof NoSuchMethodException) {
      return new IllegalArgumentException("Reflection Exception.", e);
    } else if (e instanceof InvocationTargetException) {
      return new RuntimeException("Reflection Exception.", ((InvocationTargetException) e).getTargetException());
    } else if (e instanceof RuntimeException) {
      return (RuntimeException) e;
    }
    return new RuntimeException("Unexpected Checked Exception.", e);
  }
}
