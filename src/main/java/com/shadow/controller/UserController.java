package com.shadow.controller;

import com.shadow.annotation.BrowseFrequency;
import com.shadow.dao.AreaServerDao;
import com.shadow.dao.PersonDao;
import com.shadow.domain.Person;
import com.shadow.domain.User;
import com.shadow.service.IUserService;
import com.shadow.vo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/list")
public class UserController {
    @Resource
    private IUserService userServiceImpl;

    @Resource
    private PersonDao personDao;

    @Resource
    private AreaServerDao areaServerDao;

    @RequestMapping("/users")
    @BrowseFrequency("ha")
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
    public JsonResult getJson(@PathVariable String name) throws Exception{
        if (name.equalsIgnoreCase("on")){
            Thread.currentThread().sleep(100000);
        }
        return JsonResult.success(name);
    }

    @RequestMapping(value = "/queryUserById", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public User queryUserById(@RequestBody User user) {
        return userServiceImpl.queryUserById(user.getId());
    }


    @RequestMapping(value = "/queryUser", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResult queryUser(@RequestBody @Valid User user, BindingResult result) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            for (ObjectError error : errorList) {
                sb.append(error.getDefaultMessage());
            }
            user = null;
        } else {
            flag = true;
            user = userServiceImpl.queryUser(user);
        }
        return new JsonResult<>(flag, sb.toString(), user);
    }


    @RequestMapping("/orders")
    @ResponseBody
    public JsonResult orders(@RequestParam int id) {
        List<Person> list = personDao.queryPersonById(id);
        // areaServerDao.queryAreaServerByGameId("G10");
        return new JsonResult();
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public User test(User user) {
        return userServiceImpl.queryUserById(user.getId());
    }


}
