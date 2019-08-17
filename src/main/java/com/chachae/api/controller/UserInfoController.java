package com.chachae.api.controller;

import com.chachae.api.dao.UserDao;
import com.chachae.api.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chachae
 * @date 2019/8/17
 */
@RestController("/userInfo")
public class UserInfoController {

  @Resource private UserService userService;

  @GetMapping("/list")
  public String list() {
    return "";
  }

  @DeleteMapping("/delete/{userUuid}")
  public Object deleteUserByUuid(@PathVariable("userUuid") String userUuid) {
    userService.remove(userUuid);
    return "";
  }
}
