package com.chachae.api.controller;
import	java.security.Principal;

import com.chachae.api.entity.UserInfo;
import com.chachae.api.service.UserInfoService;
import com.chachae.api.service.UserService;
import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chachae
 * @date 2019/8/17
 */
@RestController("/userInfo")
public class UserInfoController {

  @Resource private UserService userService;
@Resource private UserInfoService userInfoService;


  @GetMapping("/list")
  public String list() {
    return "";
  }

  @PostMapping("/self")
  public void selfInfo(UsernamePasswordToken token){

  }

  @DeleteMapping("/delete/{userUuid}")
  public Object deleteUserByUuid(@PathVariable("userUuid") String userUuid) {
    userService.remove(userUuid);
    return "";
  }
}
