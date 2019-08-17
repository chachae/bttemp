package com.chachae.api.exception;

/**
 * 自定义异常处理<br>
 * 继承了RuntimeException，将错误交给了SpringExceptionResolver处理
 *
 * @author chachae
 * @date 2019/8/4
 */
public class PermissionException extends RuntimeException {

  public PermissionException() {
    super();
  }

  public PermissionException(String message) {
    super(message);
  }

  public PermissionException(String message, Throwable cause) {
    super(message, cause);
  }

  public PermissionException(Throwable cause) {
    super(cause);
  }

  protected PermissionException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
