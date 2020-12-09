package com.soe.langya.web;

import com.soe.langya.pojo.RespBean;
import com.soe.langya.pojo.User;
import com.soe.langya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = {"*", "null"})
@RestController
public class LoginRegController {
    @Autowired
    private UserService userService;

    @RequestMapping("/listUser")
    public Map<String, String> listUser(Model m) {
        Map<String,String> test = new HashMap<>();
        test.put("hello", "value");
        return test;
    }
    @RequestMapping("/login")
    public RespBean login(@RequestBody User user) {
        int result = userService.login(user);
        if (result == 0){
            //此处返回为UserDetail 强转为User
            User userInfo = userService.loadUserByUsername(user.getUser_name());
            System.out.println(userInfo);
            return new RespBean("登录成功", "success", userInfo);
        } else if ( result == 1) {
            return new RespBean("用户名不存在", "error");
        } else {
            return new RespBean("用户名或密码不正确", "error");
        }
    }

    /**
     * sign in a User
     * when result is
     * 0: Success
     * 1: Duplicate User Name
     * others: Fail
     * @param user
     * @return msg for front end
     */
    @RequestMapping("/reg")
    public RespBean reg(@RequestBody User user) {
        int result = userService.reg(user);
        if (result == 0){
            return new RespBean("注册成功", "success");
        } else if ( result == 1) {
            return new RespBean("用户名重复", "error");
        } else {
            return new RespBean("注册失败", "error");
        }
    }







}
