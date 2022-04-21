package com.mycli.demo.service;

import com.mycli.demo.model.User;

public interface IsUserservice {
    /**
     * 添加用户信息
     *
     * @param user 用户信息
     * @return 数据库处理
     */
    public int  addUser(User user);
}
