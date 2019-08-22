package com.chachae.api.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * shiro的统一异常处理
 *
 * @author chachae
 * @date 2019/8/22
 */
@Slf4j
public class ShiroExceptionResolver implements HandlerExceptionResolver {

  @Override
  public ModelAndView resolveException(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    // 获取请求接口
    String url = request.getRequestURL().toString();
    // 定义ModelAndView用于返回错误信息，交给JsonData处理，以json形式返回
    ModelAndView mv;
    // 定义一个默认错误信息
    String defaultMsg = "系统错误！";
    // 请求数据异常捕获
    if (ex instanceof UnauthorizedException) {
      // 打印错误信息
      log.error("未知错误，请查询日志堆栈信息, url:" + url, ex);
      // 将捕获到的定义异常信息放入JsonData的fail方法中，通过ModelAndView返回给前端
      JsonData result = JsonData.fail(ex.getMessage());
      mv = new ModelAndView("jsonView", result.toMap());
      // 跳转到403页面
      mv.setViewName("403");
    } else if (ex instanceof UnauthenticatedException) {
      // 打印错误信息
      log.error("未知错误，请查询日志堆栈信息, url:" + url, ex);
      // 将捕获到的定义异常信息放入JsonData的fail方法中，通过ModelAndView返回给前端
      JsonData result = JsonData.fail(ex.getMessage());
      mv = new ModelAndView("jsonView", result.toMap());
      mv.setViewName("403");
    } else {
      log.error("未知错误，请查询日志堆栈信息, url:" + url, ex);
      JsonData result = JsonData.fail(defaultMsg);
      mv = new ModelAndView("jsonView", result.toMap());
    }
    return mv;
  }
}
