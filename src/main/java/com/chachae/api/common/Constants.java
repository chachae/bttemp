package com.chachae.api.common;

/**
 * @author chachae
 * @date 2019/9/19 13:41
 */
/**
 * 全局变量
 *
 * @author chachae
 * @date 2019/9/3 10:17
 */
public class Constants {

  /** 当前对象实例 */
  private static Constants constants = new Constants();

  /** 角色ID */
  public static Integer SUPER_ADMIN_ROLE_ID = 1;

  public static Integer ADMIN_ROLE_ID = 2;
  public static Integer ORDINARY_MEMBER_ROLE_ID = 3;

  /** 如果为复杂请求，axios会发送options请求预检测系统是否支持此请求，如果检测后系统支持则继续下一步 */
  public static String REQUEST_METHOD_OPTIONS = "OPTIONS";
}
