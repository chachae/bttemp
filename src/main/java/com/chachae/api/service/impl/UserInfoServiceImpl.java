package com.chachae.api.service.impl;

import com.chachae.api.dao.UserInfoDao;
import com.chachae.api.entity.UserInfo;
import com.chachae.api.entity.dto.UserInfoDTO;
import com.chachae.api.service.UserInfoService;
import com.google.common.collect.Lists;
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
  public UserInfoDTO findByUserUuid(String userUuid) {
    UserInfo userInfo = userInfoDao.selectByUserUuid(userUuid);
    return UserInfoDTO.dto(userInfo);
  }

  @Override
  public List<UserInfoDTO> getList() {
    List<UserInfo> list = userInfoDao.selectAllList();
    List<UserInfoDTO> dtoList = Lists.newArrayList();
    for (UserInfo userInfo : list) {
      UserInfoDTO dto = UserInfoDTO.dto(userInfo);
      dtoList.add(dto);
    }
    return dtoList;
  }

  @Override
  public int add(UserInfo userInfo) {
    return userInfoDao.insert(userInfo);
  }

  @Override
  public List<UserInfoDTO> getByName(String name) {
    List<UserInfo> list = userInfoDao.selectByName(name);
    List<UserInfoDTO> dtoList = Lists.newArrayList();
    for (UserInfo userInfo : list) {
      UserInfoDTO dto = UserInfoDTO.dto(userInfo);
      dtoList.add(dto);
    }
    return dtoList;
  }

  @Override
  public UserInfoDTO getByUserUuid(String userUuid) {
    UserInfo userInfo = userInfoDao.selectByUserUuid(userUuid);
    return UserInfoDTO.dto(userInfo);
  }
}
