package com.shadow.service;

import com.shadow.dao.UserDao;
import com.shadow.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by alice on 201/10/27.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User queryUserById(int id) {
        return userDao.queryUserById(id);
    }

}
