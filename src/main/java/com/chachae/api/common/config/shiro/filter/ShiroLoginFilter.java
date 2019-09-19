package com.chachae.api.common.config.shiro.filter;

import cn.hutool.core.lang.Dict;
import com.chachae.api.common.Constants;
import com.chachae.api.util.JsonUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * shiro自定义登陆过滤器<br>
 * 重写shiro的【authc】认证规则，具体是shiroConfig中的authc
 *
 * @author chachae
 * @date 2019/8/29 16:16
 */
public class ShiroLoginFilter extends FormAuthenticationFilter {

  /**
   * 如果isAccessAllowed返回false 则执行onAccessDenied
   *
   * @param request 请求
   * @param response 响应
   * @param mappedValue 参数
   * @return isAccessAllowed
   */
  @Override
  protected boolean isAccessAllowed(
      ServletRequest request, ServletResponse response, Object mappedValue) {
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    if (request instanceof HttpServletRequest) {
      // 如果检测到了options请求，返回自定义（允许PUT,DELETE请求），允许发送此请求的源继续下一个请求
      if (Constants.REQUEST_METHOD_OPTIONS.equals(
          ((HttpServletRequest) request).getMethod().toUpperCase())) {
        // 设置响应状态，获取状态
        httpServletResponse.setHeader(
            "Status", String.valueOf(((HttpServletResponse) response).getStatus()));
        // 设置服务器，用于伪装服务器
        httpServletResponse.setHeader("Server", "nginx");
        // 允许的请求
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "PUT,DELETE");
        // 设置源请求编码
        httpServletResponse.setCharacterEncoding("UTF-8");
        // 返回自定义响应头
        return super.isAccessAllowed(request, httpServletResponse, mappedValue);
      }
    }
    // 设置源请求头
    assert request instanceof HttpServletRequest;
    httpServletResponse.setHeader(
        "Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("Origin"));
    httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
    httpServletResponse.setHeader("Server", "nginx");
    httpServletResponse.setHeader(
        "Status", String.valueOf(((HttpServletResponse) response).getStatus()));
    // 设置源请求编码
    httpServletResponse.setCharacterEncoding("UTF-8");
    // 若没有检测到option预检测请求则默认允许跨域源给服务器发送请求
    return super.isAccessAllowed(request, httpServletResponse, mappedValue);
  }

  /**
   * 在访问controller前判断是否登录，返回json，不进行重定向。
   *
   * @param request 请求
   * @param response 响应
   * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
   */
  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
      throws IOException {
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    // 这里是个坑，如果不设置的接受的访问源，那么前端都会报跨域错误，因为这里还没到application-shiro.xml的配置里面
    // 设置源请求头
    httpServletResponse.setHeader(
        "Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("Origin"));
    httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
    // 设置源请求编码
    httpServletResponse.setCharacterEncoding("UTF-8");
    httpServletResponse.setContentType("application/json");
    // 前端的路由用code或者ret判断是否登录
    Dict dict = Dict.create().set("ret", false).set("msg", "请先登录").set("code", -1000);
    httpServletResponse.getWriter().write(JsonUtils.obj2String(dict));
    return false;
  }
}
