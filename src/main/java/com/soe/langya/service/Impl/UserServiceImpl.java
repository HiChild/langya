package com.soe.langya.service.Impl;

import com.soe.langya.mapper.UserMapper;
import com.soe.langya.pojo.User;
import com.soe.langya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired private UserMapper userMapper;

    @Override
    public int register(User user) {
       return userMapper.save(user);
    }

    @Override
    public User login(User user) {
        return userMapper.findByUserNameAndPassword(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
