package com.chachae.api.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户详细信息实体类
 *
 * @author chachae
 * @date 2019/7/11
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

  /** 成员信息ID */
  private String userInfoUuid;

  /** 成员ID */
  private String userUuid;

  /** 成员名字 */
  private String name;

  /** 邮箱 */
  private String email;

  /** 教务系统密码 */
  private String eduPassword;

  /** 方向 */
  private Integer orientation;

  /** 性别. 1:男 0:女 */
  private Integer sex;

  /** 头像 */
  private String icon;

  /** 联系方式 */
  private String phone;

  /** 简介 */
  private String info;

  /** 特长 */
  private String speciality;

  /** 1老师/2毕业生/3在校4考核 */
  private Integer note;

  /** 联表User */
  private User user;
}
