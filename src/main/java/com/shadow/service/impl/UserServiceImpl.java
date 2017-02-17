package com.shadow.service.impl;

import com.shadow.dao.UserDao;
import com.shadow.domain.User;
import com.shadow.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserDao userDao;

    @Override
    public User queryUserById(int id) {
        return userDao.queryUserById(id);
    }

    @Override
    public User queryUser(User user) {
        return userDao.queryUser(user);
    }

    @Override
    public int insertBatch(List<User> list) {
        return userDao.insertBatch(list);
    }
}
