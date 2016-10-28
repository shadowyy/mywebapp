package com.shadow.controller;

import com.shadow.domain.User;
import com.shadow.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by alice on 2016/10/10.
 */
@Controller
@RequestMapping("/list")
public class UserController {
    @Resource
    private IUserService userServiceImpl;

    @RequestMapping("/users")
    public String users(ModelMap model) {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "h", 23));
        list.add(new User(2, "i", 21));
        list.add(new User(3, "j", 19));
        model.put("users", list);
        return "list";
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    @ResponseBody
    public User getJson(@PathVariable String name) {
        User user = new User();
        user.setName(name);
        user.setId(1);
        user.setAge(20);
        return user;
    }

    @RequestMapping(value = "/queryUser", method = RequestMethod.GET)
    @ResponseBody
    public User queryUserById(@RequestBody User user) {
        return userServiceImpl.queryUserById(user.getId());
    }

}
