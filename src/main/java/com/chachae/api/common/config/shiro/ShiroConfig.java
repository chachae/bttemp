package com.chachae.api.common.config.shiro;

import com.chachae.api.common.config.shiro.filter.ShiroLoginFilter;
import com.google.common.collect.Maps;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Map;

/**
 * shiro配置
 *
 * @author chachae
 * @date 2019/8/16
 */
@Configuration
public class ShiroConfig {

  @Bean
  public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager);
    // 获取filters
    Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
    // 将自定义 的FormAuthenticationFilter注入shiroFilter中
    filters.put("authc", new ShiroLoginFilter());
    Map<String, String> map = Maps.newLinkedHashMap();
    // 配置不会被拦截的链接，顺序判断
    map.put("/static/**", "anon");
    map.put("/webjars/**", "anon");
    map.put("/login", "anon");
    map.put("/logout", "logout");
    map.put("/**", "authc");
    // 配置拦截器
    shiroFilterFactoryBean.setLoginUrl("/login");
    shiroFilterFactoryBean.setSuccessUrl("/main");
    shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
    return shiroFilterFactoryBean;
  }

  /**
   * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 ）
   *
   * @return hashedCredentialsMatcher
   */
  @Bean
  public HashedCredentialsMatcher hashedCredentialsMatcher() {
    HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
    // 散列算法:这里使用MD5算法;
    hashedCredentialsMatcher.setHashAlgorithmName("md5");
    // 散列的次数，比如散列两次，相当于 md5(md5(""));
    hashedCredentialsMatcher.setHashIterations(2);
    return hashedCredentialsMatcher;
  }

  @Bean
  public MyShiroRealm myShiroRealm() {
    MyShiroRealm myShiroRealm = new MyShiroRealm();
    myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
    return myShiroRealm;
  }

  @Bean
  public SecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(myShiroRealm());
    return securityManager;
  }

  /**
   * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
   *
   * @param securityManager 安全管理器
   * @return authorizationAttributeSourceAdvisor
   */
  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
      SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
        new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
  }
}
