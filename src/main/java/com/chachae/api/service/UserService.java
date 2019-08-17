package com.chachae.api.service;

import com.chachae.api.entity.User;

import java.util.List;

/**
 * @author chachae
 * @date 2019/8/12
 */
public interface UserService {

  /**
   * 获取成员登陆信息列表
   *
   * @return 成员登陆信息列表
   */
  List<User> getList();

  /**
   * 增加成员
   *
   * @param user 成员输入的信息
   * @return UUID
   */
  int add(User user);

  /**
   * 通过uuid删除成员信息，同时t_user_info中该成员的详细信息也会一同删除
   *
   * @param userUuid 成员的uuid
   * @return int型删除状态标识
   */
  int remove(String userUuid);


  /**
   * 修改详细信息
   *
   * @param user 成员登陆修改信息
   * @return 返回int值
   */
  int modify(User user);
}
