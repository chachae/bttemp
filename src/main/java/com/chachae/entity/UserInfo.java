package com.chachae.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chachae
 * @date 2019/8/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Accessors(chain = true)
@SuppressWarnings("serial")
public class UserInfo implements Serializable {

  /** 成员ID */
  private String userUuid;

  /** 成员学号/老师可用手机号 */
  private String stuId;

  /** 登录密码 */
  private String password;

  /** 1为超管,2为普管，3没有权限 */
  private Integer role;
}
