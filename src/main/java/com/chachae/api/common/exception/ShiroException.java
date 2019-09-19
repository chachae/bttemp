package com.chachae.api.common.exception;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * shiro异常处理
 *
 * @author chachae
 * @date 2019/8/22
 */
@ControllerAdvice
public class ShiroException extends RuntimeException {

  @ExceptionHandler
  public String exception(Exception ex) {
    if (ex instanceof UnauthenticatedException) {
      return "403";
    } else if (ex instanceof UnauthorizedException) {
      return "403";
    }
    return null;
  }
}
