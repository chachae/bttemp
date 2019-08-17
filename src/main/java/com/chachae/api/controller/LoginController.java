package com.chachae.api.controller;

import com.chachae.api.common.JsonData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author chachae
 * @date 2019/8/16
 */
@Controller
public class LoginController {

  @ResponseBody
  @PostMapping("/login")
  public Object login(String username, String password) {
    // 从SecurityUtils里边创建一个 subject
    Subject subject = SecurityUtils.getSubject();
    // 在认证提交前准备 token（令牌）
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    // 执行认证登陆
    try {
      subject.login(token);
    } catch (UnknownAccountException uae) {
      return JsonData.fail("未知账户");
    } catch (IncorrectCredentialsException ice) {
      return JsonData.fail("密码不正确");
    } catch (AuthenticationException ae) {
      return JsonData.fail("用户名或密码不正确");
    }
    if (subject.isAuthenticated()) {
      return JsonData.success(1, "登录成功");
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

  @GetMapping("/403")
  public String unauthorizedRole() {
    System.out.println("------没有权限-------");
    return "403";
  }
}
