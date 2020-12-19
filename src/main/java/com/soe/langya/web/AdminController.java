package com.soe.langya.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soe.langya.pojo.PageSize;
import com.soe.langya.pojo.ReqBean;
import com.soe.langya.pojo.RespBean;
import com.soe.langya.pojo.User;
import com.soe.langya.service.AdminService;
import com.soe.langya.service.PageService;
import com.soe.langya.service.UserService;
import com.soe.langya.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*", "null"})
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    PageService pageService;
    @RequestMapping("/freeze")
    public RespBean freeze (@RequestBody User user) {
        User theOne = userService.findById(user.getUser_id());
        Integer res = userService.freeze(theOne);
        if (res == 0) {
            return new RespBean("冻结成功", "success");
        }else {
            return new RespBean("解冻失败","error");
        }
    }

    @RequestMapping("/unFreeze")
    public RespBean unFreeze (@RequestBody User user) {
        User theOne = userService.findById(user.getUser_id());
        Integer res = userService.unFreeze(theOne);
        if (res == 0) {
            return new RespBean("解冻成功", "success");
        }else {
            return new RespBean("解冻失败","error");
        }
    }

    @RequestMapping("/status")
    public RespBean status(@RequestBody User user) {
        Integer res = userService.updateStatus(user);
        if (res == 0) {
            return new RespBean("更改成功", "success");
        }else {
            return new RespBean("更改失败","error");
        }
    }


//    @RequestMapping("/show")
//    public RespBean show() {
//        List<User> users= userService.findAll();
//
//        if (users != null) {
//            RespBean respBean = new RespBean("查询成功", "success");
//            respBean.setUsers(users);
//            return respBean;
//        } else {
//            return new RespBean("查询失败", "error");
//        }
//    }

    /**
     * 此方法已经被/page/findAllUser取代
     * @return
     */
    @RequestMapping("/show")
    public PageInfo<User> show() {
        List<User> users= userService.findAll();
        PageInfo<User> page = new PageInfo<>(users);
        return page;
    }

    @RequestMapping("/update")
    public RespBean update(@RequestBody User user) {
        Integer res = userService.update(user);
//        List<User> users= userService.findAll();&& users != null
        if ( res == 0) {
            RespBean respBean = new RespBean("查询成功", "success");
//            respBean.setUsers(users);
            return respBean;
        } else {
            return new RespBean("查询失败", "error");
        }
    }

    /**
     * 接口太危险
     * @return
     */
//    @RequestMapping("/delete")
//    public RespBean delete(@RequestBody User user) {
//        Integer res = userService.deleteById(user);
////        List<User> users= userService.findAll();users != null &&
//        if (res == 0) {
//            RespBean respBean = new RespBean("删除成功", "success");
////            respBean.setUsers(users);
//            return respBean;
//        } else {
//            return new RespBean("删除失败", "error");
//        }
//    }

    @RequestMapping("/cleanTag")
    public String cleanTag () {
        Integer res = adminService.cleanTag();
        return "clean complete ，total "+res+" tags had been delete";
    }
    @RequestMapping("/cleanAskTag")
    public String cleanAskTag () {
        Integer res = adminService.cleanAskTag();
        return "clean complete total "+res+" askTag had been delete";
    }

    @RequestMapping("/findAllUser")
    public PageInfo<User> findAllArticle (@RequestBody ReqBean reqBean, @RequestHeader Integer userId) {
        List<User> users = pageService.findAllUser(userId);
        return PageUtils.getPageInfo(reqBean.getStart(), reqBean.getSize(), users);
    }
    @RequestMapping("/test")
    public String test (){
        return "test";
    }
}
