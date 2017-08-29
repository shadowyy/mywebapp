package com.shadow.controller;

import com.shadow.domain.Users;
import com.shadow.service.IUsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shadowyy
 * @version 2017/8/29 13:52
 */
@Controller
@RequestMapping("/users")
public class UsersController {
    @Resource
    private IUsersService usersServiceImpl;

    @RequestMapping(value = "/deleteByPrimaryKey")
    @ResponseBody
    public int deleteByPrimaryKey(Integer id) {
        return usersServiceImpl.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/updateByPrimaryKeySelective")
    @ResponseBody
    public int updateByPrimaryKeySelective(@RequestBody Users users) {
        return usersServiceImpl.updateByPrimaryKeySelective(users);
    }

    @RequestMapping(value = "/insertSelective")
    @ResponseBody
    public int insertSelective(@RequestBody Users users) {
        return usersServiceImpl.insertSelective(users);
    }

    @RequestMapping(value = "/querySelective")
    @ResponseBody
    public List<Users> querySelective(@RequestBody Users users) {
        return usersServiceImpl.querySelective(users);
    }
}
