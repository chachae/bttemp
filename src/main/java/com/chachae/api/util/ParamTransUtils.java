package com.chachae.api.util;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 数据转换工具类<br>
 *
 * @author chachae
 * @date 2019/8/2
 */
public class ParamTransUtils {

  /**
   * 计算机学院专业代码集
   *
   * @return map 专业集合
   */
  private static Map<String, String> setMajor() {
    Map<String, String> map = Maps.newHashMap();
    map.put("0401", "计算机科学与技术(动漫设计与制作)");
    map.put("0402", "计算机科学与技术(软件设计)");
    map.put("0403", "计算机科学与技术(信息网络技术)");
    map.put("0404", "计算机科学与技术");
    map.put("0406", "网络工程");
    map.put("0407", "软件工程");
    map.put("0408", "数字媒体技术");
    map.put("0409", "数据科学与大数据技术");
    map.put("0452", "计算机应用技术(网络技术)");
    map.put("0453", "计算机系统维护");
    map.put("0457", "计算机应用技术(信息管理与办公自动化)");
    map.put("0458", "计算机应用技术(平面设计)");
    map.put("0459", "计算机应用技术(IT产品营销方向)");
    return map;
  }

  /**
   * 计算该学号所属专业
   *
   * @param stuId 学号
   * @return 专业名称
   */
  public static String calculateMajor(String stuId) {
    Map<String, String> map = ParamTransUtils.setMajor();
    String majorCode = stuId.substring(4, 8);
    return map.get(majorCode);
  }
}
