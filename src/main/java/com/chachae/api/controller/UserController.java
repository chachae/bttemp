package com.chachae.api.controller;

import com.chachae.api.common.JsonData;
import com.chachae.api.entity.User;
import com.chachae.api.entity.UserInfo;
import com.chachae.api.service.UserInfoService;
import com.chachae.api.service.UserService;
import com.chachae.api.util.UUIDUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户基本信息控制层
 *
 * @author chachae
 * @date 2019/8/23
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Resource private UserService userService;
  @Resource private UserInfoService userInfoService;

  @PostMapping("/add")
  @RequiresPermissions("")
  public JsonData insertUser(User user) {
    String userUuid = UUIDUtils.create32UUID();
    String userInfoUuid = UUIDUtils.create32UUID();
    user.setUserUuid(userUuid);
    UserInfo userInfo = new UserInfo().setUserInfoUuid(userUuid).setUserUuid(userInfoUuid);
    userService.add(user);
    userInfoService.add(userInfo);
    return JsonData.success(1, "增加成功");
  }

  @PutMapping("/update")
  public JsonData updateUser(User user) {
    userService.modify(user);
    // 使用shiro退出
    SecurityUtils.getSubject().logout();
    return JsonData.success(1, "修改成功，请重新登陆");
  }

  @DeleteMapping("/delete/{userUuid}")
  @RequiresPermissions("userInfo:del")
  public JsonData deleteUserByUuid(@PathVariable("userUuid") String userUuid) {
    userService.remove(userUuid);
    return JsonData.success(1, "删除成功");
  }
}
