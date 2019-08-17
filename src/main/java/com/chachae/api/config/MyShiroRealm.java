package com.chachae.api.config;

import com.chachae.api.entity.User;
import com.chachae.api.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author chachae
 * @date 2019/8/16
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

  @Resource private LoginService loginService;

  /**
   * 授权的方法、权限配置（本数系统数据库不包含权配置表，可省略）
   *
   * @param principalCollection Realm验证成功的身份信息
   * @return 该身份的权限
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    return null;
  }

  /**
   * 认证方法
   *
   * @param token 用户提交的身份信息（账号、密码）等的凭据
   * @return Realm验证成功的身份信息
   * @throws AuthenticationException 抛出验证失败的异常
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    // 获取用户输入的账号
    String username = (String) token.getPrincipal();
    User user = loginService.getUserByName(username);
    if (StringUtils.isEmpty(user)) {
      return null;
    }
    // 这里验证authenticationToken和simpleAuthenticationInfo的信息
    return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
  }
}
