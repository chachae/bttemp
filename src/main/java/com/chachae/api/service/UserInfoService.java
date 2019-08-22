package com.chachae.api.service;

import com.chachae.api.entity.UserInfo;

import java.util.List;

/**
 * @author chachae
 * @date 2019/8/12
 */
public interface UserInfoService {

  /**
   * 删除信息
   *
   * @return int
   */
  int remove(String userUuid);

  /**
   * 修改详细信息
   *
   * @param userInfo 用户修改信息
   * @return 返回int值
   */
  int modify(UserInfo userInfo);

  /**
   * 通过UUID查询该成员详细信息
   *
   * @param userUuid 成员UUID
   * @return 该成员的详细信息
   */
  UserInfo findByUserUuid(String userUuid);

  /**
   * 获取成员详细信息列表
   *
   * @return 成员详细信息列表
   */
  List getList();

  /**
   * 添加成员登录信息
   *
   * @param userInfo 成员登陆信息
   * @return int添加状态
   */
  int add(UserInfo userInfo);

  /**
   * 获取该成员登陆信息列表
   *
   * @param name 成员姓名
   * @return 成员登陆信息列表
   */
  List<UserInfo> getByName(String name);

  /**
   * 通过姓名获取成员登录信息列表
   *
   * @param userUuid 成员uuid
   * @return该成员的详细信息
   */
  UserInfo getByUserUuid(String userUuid);
}
