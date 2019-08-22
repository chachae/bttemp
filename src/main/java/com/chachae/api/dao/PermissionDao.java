package com.chachae.api.dao;

import com.chachae.api.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chachae
 * @date 2019/8/22
 */
@Mapper
public interface PermissionDao {

  /**
   * 通过角色查询权限
   *
   * @param role 权限等级
   * @return 所属角色的权限列表
   */
  List<Permission> selectByRoleId(Integer role);
}
