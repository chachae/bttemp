package com.chachae.api.util;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 获取Request对象相关信息的工具类
 *
 * @author chachae
 * @date 2019/9/3 16:17
 */
public class RequestUtils {
  private static final String SERVER_PORT = "80";
  /**
   * 获取request对象
   *
   * @return request
   */
  public static HttpServletRequest getRequest() {
    try {
      return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    } catch (Exception e) {
      return null;
    }
  }
  /**
   * 项目的真实路径
   *
   * @return String
   */
  public static String getPjoPath() {
    try {
      return // 项目的真实路径
      StringUtils.replace(
          Objects.requireNonNull(getRequest()).getServletContext().getContextPath(), "/", "\\");
    } catch (Exception e) {
      return null;
    }
  }
  /**
   * 获取客户端请求的路径名，如：/object/delObject
   *
   * @return String
   */
  public static String getServletPath() {
    try {
      return // 项目的真实路径
      Objects.requireNonNull(getRequest()).getServletPath();
    } catch (Exception e) {
      return null;
    }
  }
  /**
   * 获取服务器地址，如：localhost
   *
   * @return String
   */
  public static String getServerName() {
    try {
      return // 项目的真实路径
      Objects.requireNonNull(getRequest()).getServerName();
    } catch (Exception e) {
      return null;
    }
  }
  /**
   * 获取服务器端口，如8080
   *
   * @return String
   */
  public static String getServerPort() {
    try {
      return // 项目的真实路径
      Objects.requireNonNull(getRequest()).getServerPort() + "";
    } catch (Exception e) {
      return SERVER_PORT;
    }
  }
  /**
   * 获用户地址，如：127.0.0.1
   *
   * @return String
   */
  public static String getRemoteAddr() {
    try {
      String remoteAddr = Objects.requireNonNull(getRequest()).getHeader("X-Real-IP");
      if (ObjectUtil.isNotEmpty(remoteAddr)) {
        remoteAddr = getRequest().getHeader("X-Forwarded-For");
      } else if (ObjectUtil.isNotEmpty(remoteAddr)) {
        remoteAddr = getRequest().getHeader("Proxy-Client-IP");
      } else if (ObjectUtil.isNotEmpty(remoteAddr)) {
        remoteAddr = getRequest().getHeader("WL-Proxy-Client-IP");
      }
      return remoteAddr != null ? remoteAddr : getRequest().getRemoteAddr();
    } catch (Exception e) {
      return "";
    }
  }
  /**
   * 获取项目的访问路径，如： localhost:8080/xx
   *
   * @return String
   */
  public static String getObjUrl() {
    return getServerName() + ":" + getServerPort() + getServletPath();
  }
}
