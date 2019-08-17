package com.chachae.api.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 环绕通知（增强）log输出配置
 *
 * @author chachae
 * @date 2019/8/11
 */
@Aspect
@Component
public class AroundAdvice {
  /** 为每个AroundLogger.class声明一个Logger私有属性 */
  private static final Logger log = LoggerFactory.getLogger(AroundAdvice.class);

  /** 定义切入点，切入点为com.example.demo 下的所有函数 */
  @Pointcut("execution(* com.chachae.api.service..*.*(..) )")
  public void webLog() {}

  /**
   * 环绕通知方法
   *
   * @param pjp 切入点
   * @return 日志信息
   * @throws Throwable 抛出所有异常
   * @see <a href="https://zhidao.baidu.com/question/378829457.html">Throwable和Exception的区别</a>
   */
  @Around("webLog()")
  public Object aroundLogger(ProceedingJoinPoint pjp) throws Throwable {
    // 所有log输出采用占位符的方式
    log.info(
        "调用 {} 的 {} 方法,方法入参: {} (目标执行前执行!)",
        pjp.getTarget(),
        pjp.getSignature().getName(),
        Arrays.toString(pjp.getArgs()));
    // 目标执行前执行 = 前置增强
    try {
      // 目标执行后执行 = 后置增强
      Object result = pjp.proceed();
      log.info(
          "调用 {} 的 {} 方法,方法返回值: {} (目标不出现异常时执行!)",
          pjp.getTarget(),
          pjp.getSignature().getName(),
          result);
      return result;
    } catch (Throwable e) {
      // 目标出现异常时执行 = 异常增强
      log.error("{} 方法异常: {} (目标异常时执行!)", pjp.getSignature().getName(), e);
      throw e;
    } finally {
      // 目标无论有没有发生异常都会执行 = 最终增强
      log.info("{} 方法执行结束! (执行完毕!)", pjp.getSignature().getName());
    }
  }
}
