package com.chachae.api.util;

import java.util.UUID;

/**
 * 生成UUID的工具类
 *
 * @author chachae
 * @date 2019/8/123
 */
public class UUIDUtils {

  /**
   * 生成32位UUID
   *
   * @return String
   */
  public static String create32UUID() {
    // 定义变量a，接收一个36位UUID
    String a = UUID.randomUUID().toString();
    // 使用replace()替换方法，将连接号去除，转成32位UUID
    return a.replace("-", "");
  }

  /**
   * 生成36位UUID
   *
   * @return String
   */
  public static String create36UUID() {
    return UUID.randomUUID().toString();
  }
}
