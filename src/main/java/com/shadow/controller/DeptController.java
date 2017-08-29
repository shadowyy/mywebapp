package com.shadow.controller;

import com.shadow.domain.Dept;
import com.shadow.service.IDeptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shadowyy
 * @version 2017/8/29 15:28
 */
@Controller
@RequestMapping("/dept")
public class DeptController {
    @Resource
    private IDeptService deptServiceImpl;

    @RequestMapping(value = "/deleteByPrimaryKey")
    @ResponseBody
    public int deleteByPrimaryKey(Integer id) {
        return deptServiceImpl.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/updateByPrimaryKeySelective")
    @ResponseBody
    public int updateByPrimaryKeySelective(@RequestBody Dept dept) {
        return deptServiceImpl.updateByPrimaryKeySelective(dept);
    }

    @RequestMapping(value = "/insertSelective")
    @ResponseBody
    public int insertSelective(@RequestBody Dept dept) {
        return deptServiceImpl.insertSelective(dept);
    }

    @RequestMapping(value = "/querySelective")
    @ResponseBody
    public List<Dept> querySelective(@RequestBody Dept dept) {
        return deptServiceImpl.querySelective(dept);
    }
}
