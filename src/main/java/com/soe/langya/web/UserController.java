package com.soe.langya.web;

import com.soe.langya.mapper.UserMapper;
import com.soe.langya.pojo.User;
import com.soe.langya.service.Impl.UserServiceImpl;
import com.soe.langya.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    @RequestMapping("/listUser")
    public String listUser(Model m) {
        List<User> us = userServiceImpl.findAll();
        m.addAttribute("us",us);
        return "listUser";
    }
    @RequestMapping("/login")
    public String login(Model m, @RequestBody User user) {
        userServiceImpl.login(user);
        return "login";
    }
    @RequestMapping("/register")
    public String register(Model m,@RequestBody User user) {
        userServiceImpl.register(user);
        return "register";
    }
}
