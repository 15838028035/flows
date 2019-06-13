package com.thinkit.cloud.flows.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于内存管理cache
 */
public class MemoryCache implements Cache, Serializable {

  private Map map = new HashMap();

  /**
   * 
   * 缓存内部类
   *
   */
  public class Value {
    long gmtCreate = System.currentTimeMillis();
    int expiration;
    Object value;

    public Value(Object value, int expiration) {
      this.value = value;
      this.expiration = expiration * 1000;
    }
  }

  public void add(String key, Object value, int expiration) {
    map.put(key, new Value(value, expiration));
  }

  public void clear() {
    map.clear();
  }

  /**
   * 递减的元素的值
   * 
   * @param key 缓存key
   * @param by 递增值
   * @return  递减的元素的值
   */
  public long decr(String key, int by) {
    Long v = (Long) map.get(key);
    if (v == null) {
      v = -(long) by;
    } else {
      v = v - by;
    }
    map.put(key, v);
    return v;
  }

  public void delete(String key) {
    map.remove(key);
  }

  /**
   * 根据key得到一个元素
   * 
   * @param key 缓存key
   * @return 对象
   */
  public Object get(String key) {
    Value value = (Value) map.get(key);
    if (value == null)  {
      return null;
    }
    boolean isTimeout = (value.gmtCreate + value.expiration) <= System.currentTimeMillis();
    if (isTimeout) {
      delete(key);
      return null;
    } else {
      return value.value;
    }
  }

  /**
   * 批量查找元素
   * 
   * @param keys 缓存key
   *          
   * @return map
   */
  public Map<String, Object> get(String[] keys) {
    Map<String, Object> result = new HashMap();
    for (String key : keys) {
      Object value = get(key);
      result.put(key, value);
    }
    return result;
  }

  /**
   * 递增元素的值
   * 
   * @param key
   *          缓存key
   * @param by
   *          递增量
   * @return long 递增元素的值
   */
  public long incr(String key, int by) {
    Long v = (Long) map.get(key);
    if (v == null) {
      v = (long) by;
    } else {
      v = v + by;
    }
    map.put(key, v);
    return v;
  }

  public void replace(String key, Object value, int expiration) {
    map.put(key, new Value(value, expiration));
  }

  public boolean safeAdd(String key, Object value, int expiration) {
    map.put(key, new Value(value, expiration));
    return true;
  }

  public boolean safeDelete(String key) {
    return map.remove(key) != null;
  }

  public boolean safeReplace(String key, Object value, int expiration) {
    map.put(key, new Value(value, expiration));
    return true;
  }

  public boolean safeSet(String key, Object value, int expiration) {
    map.put(key, new Value(value, expiration));
    return true;
  }

  public void set(String key, Object value, int expiration) {
    map.put(key, new Value(value, expiration));
  }

  public void stop() {
    map.clear();
  }

}