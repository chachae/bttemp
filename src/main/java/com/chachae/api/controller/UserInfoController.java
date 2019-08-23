package com.chachae.api.controller;

import com.chachae.api.common.JsonData;
import com.chachae.api.entity.User;
import com.chachae.api.entity.UserInfo;
import com.chachae.api.mapstruct.UserInfoConverter;
import com.chachae.api.service.UserInfoService;
import com.chachae.api.service.UserService;
import com.chachae.api.util.BeanValidator;
import com.chachae.api.util.ParamTransUtils;
import com.chachae.api.vo.UserInfoVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 详细信息控制层
 *
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
    for (UserInfoVO vo : voList) {
      // 调用工具类，将VO对象中的数字数据转化成其中文释义
      vo.setMajor(ParamTransUtils.calculateMajor(vo.getStuId()));
      // 使用BeanValidator校验数据
      BeanValidator.check(vo);
    }
    return JsonData.success(voList, "查询成功");
  }

  /**
   * 获取个人信息
   *
   * @return 个人信息
   */
  @GetMapping("/self")
  public JsonData selfInfo() {
    User user = (User) SecurityUtils.getSubject().getPrincipal();
    UserInfo selfInfo = userInfoService.getByUserUuid(user.getUserUuid());
    UserInfoVO vo = UserInfoConverter.INSTANCES.toUserInfoVO(selfInfo);
    BeanValidator.check(vo);
    return JsonData.success(vo, "获取个人信息成功");
  }

  /**
   * 修改详细信息
   *
   * @param userInfo 用户输入的详细信息
   * @return JSON，用true / false判断
   */
  @PutMapping("/update")
  public JsonData updateInfo(UserInfo userInfo) {
    userInfoService.modify(userInfo);
    return JsonData.success(1, "修改成功！");
  }
}
