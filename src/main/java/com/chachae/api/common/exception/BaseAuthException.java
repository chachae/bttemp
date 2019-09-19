package com.chachae.api.common.exception;

import com.chachae.api.common.JsonData;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 抽象认证失败的异常，由AuthController继承<br>
 * SpringMVC中开启了MappingJackson2JsonView的model渲染bean<br>
 * beanName为jsonView，异常返回用modelAndView以json的形式渲染给前端
 *
 * @author chachae
 * @date 2019/9/8 10:21
 */
@ControllerAdvice
public abstract class BaseAuthException {

  /**
   * 未知账户
   *
   * @return modelAndView
   */
  @ExceptionHandler(UnknownAccountException.class)
  public ModelAndView unknownAccountException() {
    JsonData result = JsonData.fail("未知账户");
    return new ModelAndView("jsonView", result.toMap());
  }

  /**
   * 密码错误
   *
   * @return modelAndView
   */
  @ExceptionHandler(IncorrectCredentialsException.class)
  public ModelAndView incorrectCredentialsException() {
    JsonData result = JsonData.fail("密码不正确");
    return new ModelAndView("jsonView", result.toMap());
  }

  /**
   * 账号或密码错误
   *
   * @return modelAndView
   */
  @ExceptionHandler(AuthenticationException.class)
  public ModelAndView authenticationException() {
    JsonData result = JsonData.fail("用户名或密码不正确");
    return new ModelAndView("jsonView", result.toMap());
  }

  /**
   * 未授权
   *
   * @return modelANdView
   */
  @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
  public ModelAndView authorizationException() {
    JsonData result = JsonData.fail("抱歉，权限不足。");
    return new ModelAndView("jsonView", result.toMap());
  }
}
