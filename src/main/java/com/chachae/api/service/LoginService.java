package com.chachae.api.service;

import com.chachae.api.entity.User;

/**
 * @author chachae
 * @date 2019/8/16
 */
public interface LoginService {

  /**
   * 通过登录数据查询该用户的信息
   *
   * @param username 用户名
   * @return 用户的登录信息
   */
  User getUserByName(String username);
}
