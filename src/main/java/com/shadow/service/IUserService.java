package com.shadow.service;

import com.shadow.domain.User;

import java.util.List;

/**
 * Created by alice on 2016/10/28.
 */

public interface IUserService {

    User queryUserById(int id);

    User queryUser(User user);

    int insertBatch(List<User> list);
}
