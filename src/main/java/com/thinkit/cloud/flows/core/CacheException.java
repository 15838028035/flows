package com.thinkit.cloud.flows.core;

import com.thinkit.cloud.flows.exceptions.CoreBaseRunTimeException;

/**
 * 
 * 缓存异常
 *
 */
public class CacheException extends CoreBaseRunTimeException {

  public CacheException() {
    super();
  }

  public CacheException(String message) {
    super(message);
  }

  public CacheException(Throwable cause) {
    super(cause);
  }

  public CacheException(String message, Throwable cause) {
    super(message, cause);
  }
}
