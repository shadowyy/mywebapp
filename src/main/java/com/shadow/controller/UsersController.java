package com.shadow.controller;

import com.shadow.domain.Users;
import com.shadow.service.IUsersService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shadowyy
 * @version 2017/8/31 1:26
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Resource
    private IUsersService usersServiceImpl;

    @RequestMapping(value = "/deleteByPrimaryKey")
    public int deleteByPrimaryKey(Integer id) {
        return usersServiceImpl.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Users users) {
        return usersServiceImpl.updateByPrimaryKeySelective(users);
    }

    @RequestMapping(value = "/insertSelective")
    public int insertSelective(@RequestBody Users users) {
        return usersServiceImpl.insertSelective(users);
    }

    @RequestMapping(value = "/querySelective")
    public List<Users> querySelective(@RequestBody Users users) {
        return usersServiceImpl.querySelective(users);
    }
}