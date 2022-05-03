package com.mySen.demo.dao;

import com.mySen.demo.model.Action;
import com.mySen.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-04-21
 */
@Repository
public interface UserMapper {

    public int addUserinfo(User user);

    public User selectUser(String username,String password,String role);

    public int updateUser(User user);

    public int updateUserpic(String pic,int id);

    public User selectUserbyid(int id);

    public List<User> getAllUser(String cardid, int pageStart, int pageSize);

    public int getUserCounts(String cardid);

    public List<User> getGradeTop();

    public List<User> getAlluser();

}
