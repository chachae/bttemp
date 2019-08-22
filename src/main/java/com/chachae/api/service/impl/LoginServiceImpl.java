package com.chachae.api.service.impl;

import com.chachae.api.dao.LoginDao;
import com.chachae.api.entity.User;
import com.chachae.api.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chachae
 * @date 2019/8/16
 */
@Service
public class LoginServiceImpl implements LoginService {

  @Resource private LoginDao loginDao;

  @Override
  public User getUserByName(String username) {
    /* 如果验证用户名为邮箱地址，调用<邮箱 - 密码>验证接口
    如果验证用户名为手机号码，调用<手机 - 密码>验证接口
    其他为学号，调用<学号 - 密码>验证接口*/
    if (checkPhone(username)) {
      return loginDao.selectByPhone(username);
    } else if (checkEmail(username)) {
      return loginDao.selectByEmail(username);
    } else {
      return loginDao.selectByStuId(username);
    }
  }

  /**
   * 手机号码的正则表达式验证
   *
   * @param username 用户名
   * @return true / false
   */
  private boolean checkPhone(String username) {
    // 中国大陆手机号的正则验证（11位）
    String phCn = "^[1][3578]\\d{9}$";
    return username.matches(phCn);
  }

  /**
   * 邮箱地址的正则表达式验证
   *
   * @param username 用户名
   * @return true / false
   */
  private boolean checkEmail(String username) {
    // 邮箱的正则验证
    String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    return username.matches(email);
  }
}
