package com.chachae.api.dao;

import com.chachae.api.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chachae
 * @date 2019/8/12
 */
@Mapper
public interface UserDao {

  /**
   * 获取成员登录信息列表
   *
   * @return 列表
   */
  List<User> selectList();

  /**
   * 增加成员
   *
   * @param user 成员信息
   * @return int型增加状态标识符
   */
  int insert(User user);

  /**
   * 通过uuid删除成员信息，同时t_user_info中该成员的详细信息也会一同删除
   *
   * @param userUuid 成员的uuid
   * @return int型删除状态标识
   */
  int delete(@Param("userUuid") String userUuid);

  /**
   * 修改详细信息
   *
   * @param user 成员提交的成员登录信息
   * @return int
   */
  int update(User user);
}
