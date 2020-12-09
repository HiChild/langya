//package com.soe.langya.web;
//
//import com.soe.langya.mapper.UserMapper;
//import com.soe.langya.pojo.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class InitController {
//    @Autowired
//    UserMapper userMapper;
//
//    @RequestMapping("/init")
//    public String init(Model m){
//        String user_phone;
//        String user_password;
//        String user_name;
//        String user_please;
//        int user_please_num;
//        int user_status;
//        long flag;
//        for (int i = 0; i<100; i++){
//            user_phone ="userPhone" + i;
//            user_password = "user_password" + i;
//            user_name = "user_name" + i;
//            user_please = "please";
//            user_please_num = 0;
//            user_status =  0;
//            User user = new User(user_phone,user_password,user_name,user_please,user_please_num,user_status);
//            flag = userMapper.save(user);
//            if (flag != 1) {
//                return "wrong";
//            }
//        }
//
//        return "initsuccess";
//    }
//}
