package com.chachae.api.entity;

import lombok.Data;

/**
 * @author chachae
 * @date 2019/8/22
 */
@Data
public class Role {

  /** 角色ID */
  private Integer id;

  /** 1为超管,2为普管，3没有权限 */
  private String role;
}
