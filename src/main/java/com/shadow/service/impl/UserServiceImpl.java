package com.shadow.service.impl;

import com.shadow.dao.UserDao;
import com.shadow.domain.User;
import com.shadow.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by alice on 201/10/27.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserDao userDao;

    @Override
    public User queryUserById(int id) {
        return userDao.queryUserById(id);
    }

}
