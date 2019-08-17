package com.chachae.api.service.impl;

import com.chachae.api.dao.UserDao;
import com.chachae.api.entity.User;
import com.chachae.api.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chachae
 * @date 2019/8/12
 */
@Service
public class UserServiceImpl implements UserService {

  @Resource private UserDao userDao;

  @Override
  public List<User> getList() {
    return userDao.selectList();
  }

  @Override
  public int add(User user) {
    return userDao.insert(user);
  }

  @Override
  public int remove(String userUuid) {
    return userDao.delete(userUuid);
  }

  @Override
  public int modify(User user) {
    return userDao.update(user);
  }
}
