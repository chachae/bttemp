package com.chachae.api.common.config.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * session监听器<br>
 * 配合shiroConfig下的sessionManager<br>
 * 达到以下目的<br>
 * 1. 登录成员的统计<br>
 * 2. 退出时session的删除<br>
 * 3. 过期session的删除
 *
 * @author chachae
 * @date 2019/9/14 11：52
 */
public class ShiroSessionListener implements SessionListener {

  /**
   * 统计在线人数<br>
   * 使用J.U.C包的AtomicInteger类，保证线程安全，统计准确
   */
  private final AtomicInteger sessionCount = new AtomicInteger(0);

  /**
   * session创建时触发
   *
   * @param session
   */
  @Override
  public void onStart(Session session) {
    // session创建，在线人数加一
    sessionCount.incrementAndGet();
  }

  /**
   * session退出时触发
   *
   * @param session
   */
  @Override
  public void onStop(Session session) {
    // session销毁，在线人数减一
    sessionCount.decrementAndGet();
  }

  /**
   * session过期时触发
   *
   * @param session
   */
  @Override
  public void onExpiration(Session session) {
    // 会话过期,在线人数减一
    sessionCount.decrementAndGet();
  }

  /**
   * 获取在线总人数
   *
   * @return
   */
  public AtomicInteger getSessionCount() {
    return sessionCount;
  }
}
