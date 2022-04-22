package com.mySen.demo.controller;

import com.mySen.demo.model.Sign;
import com.mySen.demo.model.User;
import com.mySen.demo.service.IsSignservice;
import com.mySen.demo.service.IsUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户逻辑
 *
 * @param
 * @return
 */
@CrossOrigin("http://localhost:8081")
@RestController
public class SignController {

    @Autowired
    private IsSignservice signservice;
    /**
     * @param
     * @Desc 打卡，补卡接口
     * @return map
     */
    @PostMapping("api/Sign/addSign")
    public Map<String,Object> addUser(@RequestBody Sign sign){
        System.out.println(sign.toString());
        Map<String,Object> map = new HashMap<>();
        try{
            sign.setIsdelect(0);
            int i = signservice.addSign(sign);
            if(i>0){
                map.put("code",1);
                map.put("msg","签到成功!");
            }else{
                map.put("code",0);
                map.put("msg","签到失败!");
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
}
