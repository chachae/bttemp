package com.chachae.api.mapstruct;

import com.chachae.api.entity.UserInfo;
import com.chachae.api.vo.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * PO转VO的接口（UserInfo -> UserInfoVO）
 *
 * @author chachae
 * @date 2019/8/3
 */
@Mapper
public interface UserInfoConverter {

  /** 获取该类自动生成的实现类的实例 接口中的属性都是 public static final 的 方法都是public abstract的 */
  UserInfoConverter INSTANCES = Mappers.getMapper(UserInfoConverter.class);

  /**
   * 这个方法就是用于实现对象属性复制的方法 @Mapping 用来定义属性复制规则 source 指定源对象属性 target指定目标对象属性
   *
   * @param userInfo 这个参数就是源对象，也就是需要被复制的对象
   * @return 返回的是目标对象，就是最终的结果对象
   */
  @Mappings({
    @Mapping(source = "user.stuId", target = "stuId"),
    @Mapping(source = "user.password", target = "password"),
    @Mapping(source = "user.role", target = "role"),
    @Mapping(target = "major", ignore = true)
  })
  UserInfoVO toUserInfoVO(UserInfo userInfo);

  /**
   * UserInfo列表转UserInfoVO列表
   *
   * @param userInfo UserInfo列表
   * @return UserInfoVO列表（返回的是目标对象，就是最终的结果对象）
   */
  List<UserInfoVO> toUserInfoVoList(List<UserInfo> userInfo);
}
