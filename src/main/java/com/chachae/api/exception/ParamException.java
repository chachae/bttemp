package com.chachae.api.exception;

/**
 * 自定义数据异常处理<br>
 * 继承了RuntimeException，将错误交给了SpringExceptionResolver处理<br>
 * 主要配合validator使用，用于校验VO
 *
 * @author chachae
 * @date 2019/8/4
 */
public class ParamException extends RuntimeException {
  public ParamException() {
    super();
  }

  public ParamException(String message) {
    super(message);
  }

  public ParamException(String message, Throwable cause) {
    super(message, cause);
  }

  public ParamException(Throwable cause) {
    super(cause);
  }

  protected ParamException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
