package com.chachae.api.entity;

import lombok.Data;

import java.util.List;

/**
 * @author chachae
 * @date 2019/8/22
 */
@Data
public class Permission {

  /** 主键. */
  private Integer id;

  /** 名称. */
  private String name;

  /** 资源类型，[menu|button] */
  private String resourceType;

  /** 资源路径. */
  private String url;

  /** 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view */
  private String permission;

  /** 父编号 */
  private Long parentId;

  /** 父编号列表 */
  private String parentIds;

  private Boolean available = Boolean.FALSE;

  private List<Role> roles;
}
