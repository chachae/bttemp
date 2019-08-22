package com.chachae.api.exception;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author chachae
 * @date 2019/8/22
 */
@ControllerAdvice
public class ShiroException {
  @ExceptionHandler
  public String exception(Exception ex) {
    if (ex instanceof UnauthenticatedException) {
      return "redirect:/403";
    } else if (ex instanceof UnauthorizedException) {
      return "/403";
    }
    return null;
  }
}
