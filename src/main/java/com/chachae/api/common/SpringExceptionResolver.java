package com.chachae.api.common;

import com.chachae.api.exception.ParamException;
import com.chachae.api.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类<br>
 * HandlerExceptionResolver是Spring MVC提供的，捕捉异常并处理它，才能保证程序不被终止<br>
 * Spring MVC通过它处理程序的异常，包括 Handler 映射、数据绑定以及目标方法执行时发生的异常。<br>
 * 全局异常处理，所以整个项目有关于异常的处理全部在这边处理，像shiro的未授权403就由他来处理！
 *
 * @see <a href="https://blog.csdn.net/abc997995674/article/details/80454221"/>
 * @author chachae
 * @date 2019/8/4
 */
@Slf4j
@Component
public class SpringExceptionResolver implements HandlerExceptionResolver {

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
    if (ex instanceof PermissionException || ex instanceof ParamException) {
      // 打印错误信息
      log.error("未知错误，请查询日志堆栈信息, url:" + url, ex);
      // 将捕获到的定义异常信息放入JsonData的fail方法中，通过ModelAndView返回给前端
      JsonData result = JsonData.fail(ex.getMessage());
      mv = new ModelAndView("jsonView", result.toMap());
    } else {
      log.error("未知错误，请查询日志堆栈信息, url:" + url, ex);
      // 其他错误直接返回默认地错误信息，极少会情况会触发（什么500,400之类的）
      JsonData result = JsonData.fail(defaultMsg);
      mv = new ModelAndView("jsonView", result.toMap());
    }
    return mv;
  }
}
