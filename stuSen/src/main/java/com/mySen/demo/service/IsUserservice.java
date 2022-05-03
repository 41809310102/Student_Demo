package com.mySen.demo.service;

import com.mySen.demo.model.User;

import java.util.List;

public interface IsUserservice {
    /**
     * 添加用户信息
     *
     * @param user 用户信息
     * @return 数据库处理
     */
    public int  addUser(User user);

    public User selectUser(String username,String password,String role);

    public int updateUserofid(User user);

    public int updateofheadpic(String pic,int id);

    public User selectUserbyid(int id);

    public List<User> getGradeTop();

    public List<User> getAlluser();

}
