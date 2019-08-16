package com.chachae.service;

import com.chachae.entity.User;

/**
 * @author chachae
 * @date 2019/8/16
 */

public interface LoginService {

    User getUserByName(String username);
}
