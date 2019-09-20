package com.chachae.api.controller;

import cn.hutool.core.util.ObjectUtil;
import com.chachae.api.entity.User;
import com.chachae.api.entity.UserInfo;
import com.chachae.api.entity.dto.UserInfoDTO;
import com.chachae.api.entity.vo.UserInfoVO;
import com.chachae.api.service.UserInfoService;
import com.chachae.api.service.UserService;
import com.chachae.api.util.BeanValidator;
import com.chachae.api.util.JsonData;
import com.chachae.api.util.ParamTransUtils;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 详细信息控制层
 *
 * @author chachae
 * @date 2019/8/17
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

  @Resource private UserService userService;
  @Resource private UserInfoService userInfoService;

  @GetMapping("/list")
  @RequiresPermissions("userInfo:view")
  public Object list() {
    List<UserInfoDTO> dtoList = userInfoService.getList();
    List<UserInfoVO> voList = Lists.newArrayList();
    // 判断集合对象是否为空，不为空返回成功，空返回失败
    if (ObjectUtil.isNotEmpty(dtoList)) {
      // foreach循环，调用工具类，将VO对象中的数字数据转化成其中文释义
      for (UserInfoDTO dto : dtoList) {
        UserInfoVO vo = new UserInfoVO();
        BeanUtils.copyProperties(dto, vo);
        // 调用工具类，将VO对象中的数字数据转化成其中文释义
        vo.setMajor(ParamTransUtils.calculateMajor(dto.getStuId()));
        // 使用BeanValidator校验数据
        BeanValidator.check(vo);
        voList.add(vo);
      }
      return JsonData.success(voList, "查询成功");
    }
    return JsonData.fail("没有数据");
  }

  /**
   * 获取个人信息
   *
   * @return 个人信息
   */
  @GetMapping("/self")
  public JsonData selfInfo() {
    User user = (User) SecurityUtils.getSubject().getPrincipal();
    UserInfoDTO dto = userInfoService.getByUserUuid(user.getUserUuid());
    UserInfoVO vo = new UserInfoVO();
    BeanUtils.copyProperties(dto, vo);
    vo.setMajor(ParamTransUtils.calculateMajor(dto.getStuId()));
    BeanValidator.check(vo);
    return JsonData.success(vo, "获取个人信息成功");
  }

  /**
   * 修改详细信息
   *
   * @param userInfo 用户输入的详细信息
   * @return JSON，用true / false判断
   */
  @PutMapping("/update")
  public JsonData updateInfo(UserInfo userInfo) {
    userInfoService.modify(userInfo);
    return JsonData.success(1, "修改成功！");
  }
}
