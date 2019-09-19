package com.chachae.api.common.exception;

import com.chachae.api.common.JsonData;
import com.chachae.api.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理<br>
 * 1. HandlerExceptionResolver是Spring MVC提供的，捕捉异常并处理它，才能保证程序不被终止<br>
 * 2. Spring MVC通过它处理程序的异常，包括 Handler 映射、数据绑定以及目标方法执行时发生的异常<br>
 * 3. SpringMVC中开启了MappingJackson2JsonView的model渲染bean<br>
 * 4. HandlerExceptionResolver接口的实现方法返回ModelAndView<br>
 * 5. beanName为jsonView，异常返回用modelAndView以json的形式渲染给前端
 *
 * @author chachae
 * @date 2019/9/11
 */
@Slf4j
@Configuration
public class GlobalExceptionResolver implements HandlerExceptionResolver {

  @Override
  public ModelAndView resolveException(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    // 获取请求接口
    String url = RequestUtils.getObjUrl();
    // 定义ModelAndView用于返回错误信息，交给JsonData处理，以json形式返回
    ModelAndView mv;
    // 定义一个默认错误信息
    String defaultMsg = "系统错误！";
    // 请求数据异常捕获
    if (ex instanceof ParamException) {
      // 打印错误信息
      log.error("数据异常，请查询日志信息, url:" + url, ex);
      // 将捕获到的定义异常信息放入JsonData的fail方法中，通过ModelAndView返回给前端
      JsonData result = JsonData.fail(ex.getMessage());
      // jsonView来自SpringMvc配置中，基于Jackson2的modelAndView视图处理Bean
      mv = new ModelAndView("jsonView", result.toMap());
    }
    // shiro授权错误【403】
    else if (ex instanceof UnauthorizedException || ex instanceof UnauthenticatedException) {
      JsonData result = JsonData.fail("抱歉，权限不足。");
      mv = new ModelAndView("jsonView", result.toMap());
    } else {
      // 其他无法处理的异常
      log.error("未知异常，请联系管理员查询日志, url:" + url, ex);
      JsonData result = JsonData.fail(defaultMsg);
      mv = new ModelAndView("jsonView", result.toMap());
    }
    return mv;
  }
}
