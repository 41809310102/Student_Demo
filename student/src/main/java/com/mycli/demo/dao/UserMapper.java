package com.mycli.demo.dao;

import com.mycli.demo.model.User;
import org.springframework.stereotype.Repository;
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


}
