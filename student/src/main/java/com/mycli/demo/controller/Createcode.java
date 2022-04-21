package com.mycli.demo.controller;

import com.mycli.demo.model.User;
import com.mycli.demo.util.ercode.Code;
import com.mycli.demo.util.ercode.CreateQRCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class Createcode {

    /**
     * @desc:专属二维码
     * */

    private  int Color[]= {0xFF00FF00,0x0000FF,0x000000};//分别保存为绿色，蓝色，黑二维码颜色
    @PostMapping("api/code/getcode")
    public String createCode(String userid,String url,String name,String phone){
        //创建一个二维码对象
        Code code1 = Code.builder().width(300).
                hight(300)
                .content(url+"?userid="+userid+"&name="+name+"&phone="+phone)
                .format("png")
                .type("用户")
                .onColor(0x000000)
                .offColor(0xFFFFFFFF)
                .build();
        CreateQRCode code = new CreateQRCode();
        String link = code.createQRCode(code1,userid,name,phone);
        System.out.println(link);
        return link;
    }

    @GetMapping("/api/ercode/getUserid")
    public HashMap<String,Object> getUserdata(@RequestParam String userid,@RequestParam String name,@RequestParam String phone){
        int id = Integer.parseInt(userid);
        HashMap<String,Object> map = new HashMap<>();
        System.out.println("扫码成功!");
        try{
            User user = new User();
            user.setId(id);user.setUsername(name);user.setPassword(phone);
            map.put("user",user);
            map.put("msg","success");
            map.put("code",0);
        }catch (Exception e){
            map.put("user","null");
            map.put("msg","error");
            map.put("code",-1);
        }
        return map;
    }

}
