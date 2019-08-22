package com.chachae.api.service.impl;

import com.chachae.api.dao.PermissionDao;
import com.chachae.api.entity.Permission;
import com.chachae.api.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chachae
 * @date 2019/8/22
 */
@Service
public class PermissionServiceImpl implements PermissionService {

  @Resource private PermissionDao permissionDao;

  @Override
  public List<Permission> getByRoleId(Integer role) {
    return permissionDao.selectByRoleId(role);
  }
}
