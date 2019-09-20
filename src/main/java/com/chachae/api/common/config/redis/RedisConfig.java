package com.chachae.api.common.config.redis;

import org.crazycake.shiro.RedisManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chachae
 * @date 2019/9/20 8:26
 */
@Configuration
public class RedisConfig {

  /** 配置redisManager */
  @Bean
  public RedisManager redisManager() {
    return new RedisManager();
  }
}
