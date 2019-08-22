package com.chachae.api.dao;

import com.chachae.api.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chachae
 * @date 2019/8/12
 */
@Mapper
public interface UserInfoDao {

  /**
   * 删除信息
   *
   * @param userUuid 成员UUID
   * @return int
   */
  int delete(@Param("userUuid") String userUuid);

  /**
   * 修改详细信息
   *
   * @param userInfo 成员提交的修改信息
   * @return int
   */
  int update(UserInfo userInfo);

  /**
   * 通过UUID查询该成员详细信息
   *
   * @param userUuid 成员UUID
   * @return 该成员的详细信息
   */
  UserInfo selectByUserUuid(@Param("userUuid") String userUuid);

  /**
   * 获取成员详细信息列表
   *
   * @return 成员详细信息列表
   */
  List<UserInfo> selectAllList();

  /**
   * 增加成员详细信息
   *
   * @param userInfo 成员详细信息
   * @return 整型增加情况标识
   */
  int insert(UserInfo userInfo);

  /**
   * 通过姓名获取成员登录信息列表
   *
   * @param name 成员姓名
   * @return 该成员的详细信息
   */
  List<UserInfo> selectByName(String name);
}
