package com.soe.langya.web;

import com.soe.langya.pojo.RespBean;
import com.soe.langya.pojo.User;
import com.soe.langya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*", "null"})
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/update")
    public RespBean update(@RequestBody User user) {
        Integer res = userService.update(user);
//        List<User> users= userService.findAll();users != null
        if ( res == 0) {
            RespBean respBean = new RespBean("查询成功", "success");
//            respBean.setUsers(users);
            return respBean;
        } else {
            return new RespBean("查询失败", "error");
        }
    }

}
