package com.chachae.api.service.impl;

import com.chachae.api.dao.UserInfoDao;
import com.chachae.api.entity.UserInfo;
import com.chachae.api.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chachae
 * @date 2019/8/12
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

  @Resource private UserInfoDao userInfoDao;

  @Override
  public int remove(String userUuid) {
    return userInfoDao.delete(userUuid);
  }

  @Override
  public int modify(UserInfo userInfo) {
    return userInfoDao.update(userInfo);
  }

  @Override
  public UserInfo findByUserUuid(String userUuid) {
    return userInfoDao.selectByUserUuid(userUuid);
  }

  @Override
  public List getList() {
    return userInfoDao.selectAllList();
  }

  @Override
  public int add(UserInfo userInfo) {
    return userInfoDao.insert(userInfo);
  }

  @Override
  public List<UserInfo> getByName(String name) {
    return userInfoDao.selectByName(name);
  }

  @Override
  public UserInfo getByUserUuid(String userUuid) {
    return userInfoDao.selectByUserUuid(userUuid);
  }
}
