package com.thinkit.cloud.flows.util;

import net.sf.json.JSONObject;

/**
 * JSON工具类
 *
 */
public class JsonUtil {
  /**
   * 将对象转换为json字符串
   * 
   * @param object
   *          对象
   * @return String json字符串
   */
  public static String toJson(Object object) {
    JSONObject jsonObj = JSONObject.fromObject(object);
    return jsonObj.toString();
  }

  /**
   * 根据指定类型解析json字符串，并返回该类型的对象
   * 
   * @param jsonString
   *          json字符串
   * @param clazz
   *          类类型
   * @return T
   */
  public static <T> T fromJson(String jsonString, Class<T> clazz) {
    JSONObject jsonObject = JSONObject.fromObject(jsonString);
    return (T) JSONObject.toBean(jsonObject, clazz);
  }
}
