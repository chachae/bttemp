package com.chachae.api.service;

import com.chachae.api.entity.Permission;

import java.util.List;

/**
 * @author chachae
 * @date 2019/8/22
 */
public interface PermissionService {

  /**
   * 通过角色查询权限
   *
   * @param role 权限等级
   * @return 所属角色的权限列表
   */
  List<Permission> getByRoleId(Integer role);

  /**
   * 获取所有的权限信息
   *
   * @return 权限信息列表
   */
  public List<Permission> getList();
}
