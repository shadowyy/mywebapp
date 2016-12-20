package com.shadow.controller;

import com.shadow.vo.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录
 *
 * @author yy
 * @version 2016/12/20 12:51
 */
@Controller
@RequestMapping(value = "login")
public class LoginController {

    @RequestMapping(value = "/user")
    @ResponseBody
    public JsonResult login(int userId, String pwd) {
        System.out.println("userId:" + userId + "----" + "password:" + pwd);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(String.valueOf(userId), pwd);
        String error = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (ExcessiveAttemptsException e) {
            error = "登录失败多次，账户锁定10分钟";
        } catch (AuthenticationException e) {
            // 其他错误，比如锁定，如果想单独处理请单独catch处理
            error = "其他错误：" + e.getMessage();
        }
        if (error != null) {// 出错了，返回登录页面
            return JsonResult.newIns(true,"","");
        } else {// 登录成功
            return JsonResult.newIns(false,"","");
        }

    }

}