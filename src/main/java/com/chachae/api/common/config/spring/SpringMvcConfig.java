package com.chachae.api.common.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * SpringMvc 配置
 *
 * @author chachae
 * @date 2019/8/14
 */
@Configuration
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {
  /**
   * 同源跨域配置
   *
   * @param registry 入参：注册器
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        // 跨域请求头
        .allowedHeaders("*")
        // 跨域源
        .allowedOrigins("*")
        // 是否允许证书 不再默认开启
        .allowCredentials(true)
        // 跨域允许的请求，"OPTIONS"是预请求
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        // 跨域时间
        .maxAge(1800);
  }
  /**
   * 默认servlet支持
   *
   * @param configurer 返回配置
   */
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    // 启用默认servlet处理器，主要用于处理静态资源文件
    configurer.enable();
  }

  /**
   * 全局基于Jackson2的modelAndView返回配置bean
   *
   * @return MappingJackson2JsonView配置
   */
  @Bean("jsonView")
  public MappingJackson2JsonView mappingJackson2JsonView() {
    return new MappingJackson2JsonView();
  }
}
