package com.thinkit.cloud.flows.exceptions;

/**
 * 
 * 核心异常
 *
 */
@SuppressWarnings("serial")
public class CoreBaseRunTimeException extends RuntimeException {

  public CoreBaseRunTimeException() {
    super();
  }

  public CoreBaseRunTimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public CoreBaseRunTimeException(String message) {
    super(message);
  }

  public CoreBaseRunTimeException(Throwable cause) {
    super(cause);
  }

}
