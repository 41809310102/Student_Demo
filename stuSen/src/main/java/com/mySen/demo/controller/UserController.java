package com.mySen.demo.controller;

import com.mySen.demo.model.User;
import com.mySen.demo.service.IsUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
/**
 * 用户逻辑
 *
 * @param
 * @return
 */
@RestController
public class UserController {

    @Autowired
    private IsUserservice isUserservice;
    /**
     * @param user 用户信息
     * @Desc 用户注册
     * @return map
     */
    @PostMapping("api/user/adduser")
    public Map<String,Object> addUser(@RequestBody  User user){
        System.out.println(user.toString());
        Map<String,Object> map = new HashMap<>();
        try{
            user.setIsdelect(0);
            int i = isUserservice.addUser(user);
            if(i>0){
                map.put("code",1);
                map.put("msg","注册成功!");
            }else{
                map.put("code",0);
                map.put("msg","注册失败!");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器错误！");
        }
        return map;
    }


    /**
     * @param
     * @Desc 用户登录
     * @return map
     */
    @PostMapping("api/user/loginuser")
    public Map<String,Object> loginUser(@RequestBody User users){
        Map<String,Object> map = new HashMap<>();
        try{
            User user = isUserservice.selectUser(users.getUsername(),users.getPassword(),"学生");
            if(user==null){
                map.put("code",-2);
                map.put("msg","账号或密码错误，请重新登录");
                return map;
            }
            if(user.getIsdelect()==1){
                map.put("code",0);
                map.put("msg","用户账号已经被冻结");
            }else{
                map.put("code",1);
                map.put("user",user);
                map.put("msg","登录成功");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器错误！");
        }
        return map;
    }
}
