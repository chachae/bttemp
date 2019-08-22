package com.chachae.api.controller;

import com.chachae.api.common.JsonData;
import com.chachae.api.entity.UserInfo;
import com.chachae.api.service.UserInfoService;
import com.chachae.api.service.UserService;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chachae
 * @date 2019/8/17
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

  @Resource private UserService userService;
  @Resource private UserInfoService userInfoService;

  @GetMapping("/list")
  @RequiresPermissions("userInfo:view")
  public Object list() {
    //List<UserInfo> list = userInfoService.getList();
   // return JsonData.success(list, "查询成功");
    return "/list";
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
