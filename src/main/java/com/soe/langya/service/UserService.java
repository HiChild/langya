package com.soe.langya.service;

import com.github.pagehelper.PageHelper;
import com.soe.langya.mapper.UserMapper;
import com.soe.langya.pojo.Article;
import com.soe.langya.pojo.PageSize;
import com.soe.langya.pojo.Role;
import com.soe.langya.pojo.User;
import com.soe.langya.utils.InvitationCodeUtils;
import com.soe.langya.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     *  this function is for user to sign up a account
     * @param user
     * @return 0 for Success ,1 for Duplicate Username,2 for Fail
     */
    public int reg(User user) {
        User findById = userMapper.findByName(user.getUser_name());
        if (findById != null ) {
            return 1;
        }

//        //验证是否拥有邀请码
//        if (!verify(user.getUser_please())){
//            return 2;
//        }

        //插入用户,插入之前先对密码进行加密
        user.setUser_password(MD5Utils.getDigest(user.getUser_password()));
        user.setUser_please(InvitationCodeUtils.createShareCode()); //生成随机验证码
        user.setUser_please_num(3); //默认可邀请人数为3
        user.setEnabled(true); //用户可用
        user.setUser_status(0);//设置用户为普通级别
        user.setFreeze(1);
        user.setFaceUrl("/default.jpg");
        long result = userMapper.save(user);
        boolean flag = (result == 1);
        if (flag) {
            return 0;
        } else {
            return 3;
        }
    }

    /**
     *this function is for user to sign in(login)
     * @param user the user you should provide
     * @return 0 for Success ,1 for No Username,2 for Fail
     */
    public int login(User user) {
        User byName = userMapper.findByName(user.getUser_name());
        if (byName == null) {
            return 1;
        }
        String digest = MD5Utils.getDigest(user.getUser_password());
        User byUserNameAndPassword =
                userMapper.findByUserNameAndPassword(user.getUser_name(), digest);
        if (byUserNameAndPassword != null) {
            return 0;
        } else {
            return 2;
        }
    }

    /**
     * this function is for find all of users
     * @return A List of User which include all of users
     */
    public List<User> findAll() {
        return userMapper.findAll();
    }

    /**
     *  find a user by username and password
     * @param user : the user you should provide
     * @return A User object or null
     */
    public User getUser(User user) {
        return userMapper.findByUserNameAndPassword(user.getUser_name(),user.getUser_password());
    }

    /**
     * to load user's detail information and add Role's about user.
     * @param name : the name about User which you you want to get
     * @return all of user's information
     */
    //通过用户名加载用户数据并为用户名添加角色
    public User loadUserByUsername(String name) {
        User user = userMapper.findByName(name);
        if (user == null) {
            //避免返回null，这里返回一个不含有任何值的User对象，在后期的密码比对过程中一样会验证失败
            return new User();
        }
        //查询用户所属的角色  添加
        Integer status = userMapper.findRolesById(user.getUser_id());
        /*
         * //用户的级别（状态）
         * 0为普通用户
         * 1为评论员
         * 2为系统管理员
         */
        ArrayList<Role> roles = new ArrayList<>();
        if (status == 0) {
            Role r1 = new Role(user.getUser_id(), "普通用户");
            roles.add(r1);
        } else if (status == 1) {
            Role r2 = new Role(user.getUser_id(), "审核员");
            roles.add(r2);
        } else if (status == 2) {
            Role r3 = new Role(user.getUser_id(), "超级管理员");
            roles.add(r3);
        }
        user.setRoles(roles);
        return user;
    }

    public User findById(Integer userId) {
        return userMapper.findById(userId);
    }

    public Integer freeze (User user) {
        user.setFreeze(-1);
        user.setEnabled(false);
        Integer res = userMapper.updateFreeze(user);
        return res == 1 ? 0 : -1;
    }

    public Integer unFreeze(User user) {
        user.setFreeze(1);
        user.setEnabled(true);
        Integer res = userMapper.updateFreeze(user);
        return res == 1 ? 0 : -1;
    }

    public Integer update(User user) {
        Integer res = userMapper.update(user);
        return res == 1 ? 0 : -1;
    }

    public Integer updateStatus(User user) {
        Integer res = userMapper.updateStatus(user);
        return res == 1 ? 0 : -1;
    }

    public Integer deleteById(User user) {
        Integer res = userMapper.delete(user.getUser_id());
        return res == 1 ? 0 : -1;
    }

    public Integer updateUserFaceUrl(User user) {
        Integer res = userMapper.updateUserFaceUrl(user);
        return res == 1 ? 0 : -1;
    }

    private Boolean verify(String please) {
        List<User> users = userMapper.findAll();
        for (User user : users) {
            if (please.equals(user.getUser_please())){
                if (user.getUser_please_num()>0){
                    user.setUser_please(InvitationCodeUtils.createShareCode()); //每次邀请一人后验证码更改
                    user.setUser_please_num(user.getUser_please_num() - 1);
                    userMapper.update(user);
                    return true;
                }
            }
        }
        return false;
    }





}
