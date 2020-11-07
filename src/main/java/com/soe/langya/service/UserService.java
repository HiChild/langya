package com.soe.langya.service;

import com.soe.langya.pojo.User;

import java.util.List;

public interface UserService {
    public int register (User user);

    public User login(User user);

    public List<User> findAll();
}

