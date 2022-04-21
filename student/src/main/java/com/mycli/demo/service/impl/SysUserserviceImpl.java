package com.mycli.demo.service.impl;

import com.mycli.demo.dao.UserMapper;
import com.mycli.demo.model.User;
import com.mycli.demo.service.IsUserservice;
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
}
