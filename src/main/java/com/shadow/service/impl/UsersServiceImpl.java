package com.shadow.service.impl;

import com.shadow.dao.UsersMapper;
import com.shadow.domain.Users;
import com.shadow.service.IUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shadowyy
 * @version 2017/8/29 15:42
 */
@Service
public class UsersServiceImpl implements IUsersService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Resource
    UsersMapper usersMapper;

    public int deleteByPrimaryKey(Integer id) {
        return usersMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Users users) {
        return usersMapper.updateByPrimaryKeySelective(users);
    }

    public int insertSelective(Users users) {
        return usersMapper.insertSelective(users);
    }

    public List<Users> querySelective(Users users) {
        return usersMapper.querySelective(users);
    }
}


