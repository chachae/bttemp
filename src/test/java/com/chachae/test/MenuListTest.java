package com.chachae.test;

import com.chachae.api.ShiroApplication;
import com.chachae.api.entity.Permission;
import com.chachae.api.entity.User;
import com.chachae.api.service.PermissionService;
import com.chachae.api.service.UserService;
import com.chachae.api.util.JsonUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author chachae
 * @date 2019/8/23
 */
@Slf4j
// 支持数据回滚，避免测试数据污染环境
@Transactional
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShiroApplication.class)
public class MenuListTest {

  @Resource private UserService userService;
  @Resource private PermissionService permissionService;

  @Test
  public void test1() {
    List<User> users = userService.getList();
    log.info(String.valueOf(users));
  }

  @Test
  public void test2() {
    List<Permission> permissions = permissionService.getList();
    String permissionStr = "";
    Map<String, String> map = Maps.newHashMap();
    for (Permission p : permissions) {
      map.put(p.getName(), p.getUrl());
      permissionStr = JsonUtils.obj2String(map);
    }
    log.info("获取的权限列表如下 - > {}", permissionStr);
  }
}
