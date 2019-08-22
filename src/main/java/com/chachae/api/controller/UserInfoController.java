package com.chachae.api.controller;

import com.chachae.api.common.JsonData;
import com.chachae.api.entity.UserInfo;
import com.chachae.api.mapstruct.UserInfoConverter;
import com.chachae.api.service.UserInfoService;
import com.chachae.api.service.UserService;
import com.chachae.api.util.BeanValidator;
import com.chachae.api.util.ParamTransUtils;
import com.chachae.api.vo.UserInfoVO;
import com.google.common.collect.Lists;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chachae
 * @date 2019/8/17
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

  @Resource private UserService userService;
  @Resource private UserInfoService userInfoService;

  @GetMapping("/list")
  @RequiresPermissions("userInfo:view")
  public Object list() {
    List<UserInfo> list = userInfoService.getList();
    List<UserInfoVO> voList = UserInfoConverter.INSTANCES.toUserInfoVoList(list);
    // foreach循环，调用工具类，将VO对象中的数字数据转化成其中文释义
    /*TODO Mapstruct 有问题 - warn */
    for (UserInfoVO vo : voList) {
      // 调用工具类，将VO对象中的数字数据转化成其中文释义
      vo.setMajor(ParamTransUtils.calculateMajor(vo.getStuId()));
      // 使用BeanValidator校验数据
      BeanValidator.check(vo);
    }
    return JsonData.success(voList, "查询成功");
  }

  @PostMapping("/self")
  public void selfInfo(UsernamePasswordToken token) {}

  @DeleteMapping("/delete/{userUuid}")
  @RequiresPermissions("userInfo:del")
  public Object deleteUserByUuid(@PathVariable("userUuid") String userUuid) {
    userService.remove(userUuid);
    return "";
  }
}
