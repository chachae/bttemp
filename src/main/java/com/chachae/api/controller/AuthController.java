package com.chachae.api.controller;

import com.chachae.api.common.exception.BaseAuthException;
import com.chachae.api.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 登录控制层
 *
 * @author chachae
 * @date 2019/8/16
 */
@Slf4j
@Controller
public class AuthController extends BaseAuthException {

  @ResponseBody
  @PostMapping("/login")
  public Object login(String username, String password) {
    // 从SecurityUtils里边创建一个 subject
    Subject subject = SecurityUtils.getSubject();
    // 在认证提交前准备 token（令牌）
    UsernamePasswordToken token = new UsernamePasswordToken(username, password, false);
    // 执行认证登陆
    subject.login(token);
    if (subject.isAuthenticated()) {
      // 验证成功，把token给前端
      return JsonData.success(token, "登录成功");
    } else {
      return JsonData.fail("登录失败");
    }
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String defaultLogin() {
    return "/login";
  }

  @GetMapping("/main")
  public String toMain() {
    return "main";
  }
}
