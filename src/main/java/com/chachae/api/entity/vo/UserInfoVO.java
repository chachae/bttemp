package com.chachae.api.entity.vo;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 成员详细信息VO，封装了要返回给前端的成员详细信息
 *
 * @author chachae
 * @date 2019/7/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Accessors(chain = true)
@SuppressWarnings("serial")
public class UserInfoVO implements Serializable {
  /** 成员信息ID */
  @NotBlank(message = "UUID不能为空")
  @Length(max = 32, min = 32, message = "userInfoUUID长度为32位")
  private String userInfoUuid;

  /** 成员ID */
  @NotBlank
  @Length(max = 32, min = 32, message = "userUUID长度为32位")
  private String userUuid;

  /** 学号 */
  public String stuId;

  /** 专业 */
  private String major;

  /** 成员名字 */
  private String name;

  /** 邮箱 */
  @Email(message = "不是一个合法的邮箱地址")
  private String email;

  /** 方向 */
  private String orientation;

  /** 性别 1:男 0:女 */
  private String sex;

  /** 头像 */
  private String icon;

  /** 联系方式 */
  private String phone;

  /** 简介 */
  private String info;

  /** 特长 */
  private String speciality;

  /** 1老师、2毕业生、3在校、4考核 */
  private String note;

  /** 1为超管,2为普管，3没有权限 */
  @NotBlank(message = "成员角色不能为空")
  private String role;
}
