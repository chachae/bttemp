package com.chachae.dao;

import com.chachae.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chachae
 * @date 2019/8/16
 */

@Mapper
public interface LoginDao {

  /**
   * 登录（通过学号和密码）
   *
   * @param stuId 学号
   * @return 成员登录信息
   */
  User selectByStuId(@Param(value = "stuId") String stuId);

  /**
   * 登录（通过手机号和密码）
   *
   * @param phone 手机号码
   * @return 成员登录信息
   */
  User selectByPhone(@Param(value = "phone") String phone);

  /**
   * 登录（通过邮箱和密码）
   *
   * @param email 邮箱地址
   * @return 成员登录信息
   */
  User selectByEmail(@Param(value = "email") String email);
}
