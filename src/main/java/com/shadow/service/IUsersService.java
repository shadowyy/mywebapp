package com.shadow.service;

import com.shadow.domain.Users;

import java.util.List;

/**
 * @author shadowyy
 * @version 2017/8/29 13:45
 */
public interface IUsersService {
    int deleteByPrimaryKey(Integer integer);

    int updateByPrimaryKeySelective(Users users);

    int insertSelective(Users users);

    List<Users> querySelective(Users users);
}
