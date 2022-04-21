package com.mySen.demo.service.impl;

import com.mySen.demo.dao.UserMapper;
import com.mySen.demo.model.User;
import com.mySen.demo.service.IsUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserserviceImpl  implements IsUserservice {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
       return userMapper.addUserinfo(user);
    }

    @Override
    public User selectUser(String username, String password, String role) {
      return userMapper.selectUser(username,password,role);
    }
}
