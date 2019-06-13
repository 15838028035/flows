package com.thinkit.cloud.flows.cache;

import java.util.Map;

import com.thinkit.cloud.flows.core.CacheException;

/**
 * 缓存接口
 */
public interface Cache {

  /**
   * 从缓存中清除所有的对象
   * 
   * @throws CacheException
   *           缓存异常
   */
  public void clear() throws CacheException;

  /**
   * 添加元素只有当它不存在，有异常则抛出
   **/
  public void add(String key, Object value, int expiration);

  /**
   * 添加元素只有当它不存在，并忽略掉任何异常
   * 
   * @return true则成功，false则发生异常
   **/
  public boolean safeAdd(String key, Object value, int expiration);

  /**
   * 设置cache值
   */
  public void set(String key, Object value, int expiration);

  /**
   * 设置cache值，并忽略掉任何异常
   * 
   * @return true则成功，false则发生异常
   **/
  public boolean safeSet(String key, Object value, int expiration);

  /**
   * 更换一个元素如果它已经存在，有异常则抛出
   **/
  public void replace(String key, Object value, int expiration);

  /**
   * 更换一个元素如果它已经存在，并忽略掉任何异常
   * 
   * @return true则成功，false则发生异常
   **/
  public boolean safeReplace(String key, Object value, int expiration);

  /**
   * 根据key得到一个元素
   * 
   * @param key 缓存key
   * @return 对象
   */
  public Object get(String key);

  /**
   * 批量查找元素
   * 
   * @param keys 缓存key
   *          
   * @return map
   */
  public Map<String, Object> get(String[] keys);

  /**
   * 递增元素的值
   * 
   * @param key
   *          缓存key
   * @param by
   *          递增量
   * @return long 递增元素的值
   */
  public long incr(String key, int by);

  /**
   * 递减的元素的值
   * 
   * @param key 缓存key
   * @param by 递增值
   * @return  递减的元素的值
   */
  public long decr(String key, int by);

  /**
   * 从缓存中删除一个元素
   * 
   * @param key
   *          缓存key
   */
  public void delete(String key);

  /**
   * 从缓存中删除一个元素,忽略掉任何异常，返回true则删除成功，否则失败
   * 
   * @param key 缓存key
   */
  public boolean safeDelete(String key);

  public void stop();
}
