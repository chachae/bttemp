package com.chachae.api.service;

import com.chachae.api.entity.User;

/**
 * @author chachae
 * @date 2019/8/16
 */

public interface LoginService {

    User getUserByName(String username);
}
