package com.chachae.api.util;

import com.chachae.api.vo.UserInfoVO;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据转换工具类<br>
 *
 * @author chachae
 * @date 2019/8/12
 */
public class ParamTransUtils {

  /**
   * 计算机学院专业代码集
   *
   * @return map 专业集合
   */
  private static Map<String, String> setMajor() {
    Map<String, String> map = new HashMap<>(16);
    map.put("0404", "计算机科学与技术");
    map.put("0406", "网络工程");
    map.put("0407", "软件工程");
    map.put("0408", "数字媒体技术");
    map.put("0409", "数据科学与大数据技术");
    return map;
  }

  /**
   * 成员性别对应中文含义
   *
   * @return 性别集合
   */
  private static Map<String, String> setSex() {
    Map<String, String> map = new HashMap<>(4);
    map.put("1", "男");
    map.put("0", "女");
    return map;
  }

  /**
   * 成员状态对应中文
   *
   * @return 成员状态集合
   */
  private static Map<String, String> setNote() {
    Map<String, String> map = new HashMap<>(8);
    map.put("1", "老师");
    map.put("2", "毕业生");
    map.put("3", "在校生");
    map.put("4", "考核中");
    return map;
  }

  /**
   * 成员方向（角色）对应中文
   *
   * @return 方向集合
   */
  private static Map<String, String> setOrientation() {
    Map<String, String> map = new HashMap<>(8);
    map.put("1", "后台开发");
    map.put("2", "前端设计");
    map.put("3", "UI设计");
    map.put("4", "网络安全");
    return map;
  }

  /**
   * 成员权限（角色）对应中文
   *
   * @return 角色集合
   */
  private static Map<String, String> setRole() {
    Map<String, String> map = new HashMap<>(8);
    map.put("1", "超级管理员");
    map.put("2", "普通管理员");
    map.put("3", "普通成员");
    return map;
  }

  /**
   * 计算该学号所属专业
   *
   * @param stuId 学号
   * @return 专业名称
   */
  public static String calculateMajor(String stuId) {
    Map<String, String> map = setMajor();
    String majorCode = stuId.substring(4, 8);
    return map.get(majorCode);
  }

  /**
   * 性别转换
   *
   * @param sex 性别
   * @return 性别对应中文
   */
  public static String calculateSex(String sex) {
    Map<String, String> map = setSex();
    return map.get(sex);
  }

  /**
   * 状态转换
   *
   * @param note 成员状态
   * @return 状态对应中文
   */
  public static String calculateNote(String note) {
    Map<String, String> map = setNote();
    return map.get(note);
  }

  /**
   * 方向转换
   *
   * @param orientation 成员方向
   * @return 方向对应中文
   */
  public static String calculateOrientation(String orientation) {
    Map<String, String> map = setOrientation();
    return map.get(orientation);
  }

  /**
   * 角色转换
   *
   * @param role 成员角色
   * @return 角色对应中文
   */
  public static String calculateRole(String role) {
    Map<String, String> map = setRole();
    return map.get(role);
  }

  /**
   * 将成员的详细信息（VO）中以数字代替的字段替换成其中文释义
   *
   * @param userInfoVO 成员信息
   */
  public static void converterAllUserInfo(UserInfoVO userInfoVO) {
    userInfoVO
        .setMajor(calculateMajor(userInfoVO.getStuId()))
        .setRole(calculateRole(userInfoVO.getRole()))
        .setOrientation(calculateOrientation(userInfoVO.getOrientation()))
        .setSex(calculateSex(userInfoVO.getSex()))
        .setNote(calculateNote(userInfoVO.getNote()));
  }
}
