package com.chachae.api.entity.dto;

import com.chachae.api.converter.UserInfoConverter;
import com.chachae.api.entity.UserInfo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 数据传输实体
 *
 * @author chachae
 * @date 2019/9/4 21:04
 */
@Data
public class UserInfoDTO implements Serializable {
  private static final long serialVersionUID = -6300480830831964405L;

  /** 成员信息ID */
  private String userInfoUuid;

  /** 成员ID */
  @NotBlank private String userUuid;

  /** 学号 */
  public String stuId;

  /** 专业 */
  private String major;

  /** 成员名字 */
  private String name;

  /** 邮箱 */
  private String email;

  /** 成员登录密码 */
  private String password;

  /** 教务系统密码 */
  private String eduPassword;

  /** 方向 */
  private Integer orientation;

  /** 性别 1:男 0:女 */
  private Integer sex;

  /** 头像 */
  private String icon;

  /** 联系方式 */
  private String phone;

  /** 简介 */
  private String info;

  /** 特长 */
  private String speciality;

  /** 1老师、2毕业生、3在校、4考核 */
  private Integer note;

  /** 1为超管,2为普管，3没有权限 */
  private Integer role;

  public static UserInfoDTO dto(UserInfo userInfo) {
    return UserInfoConverter.INSTANCES.toDto(userInfo);
  }
}
