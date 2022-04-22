package com.mySen.demo.controller;

import com.mySen.demo.model.User;
import com.mySen.demo.util.ercode.Code;
import com.mySen.demo.util.ercode.CreateQRCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
public class Createcode {

    /**
     * @desc:专属二维码
     * */
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private  int Color[]= {0xFF00FF00,0x0000FF,0x000000};//分别保存为绿色，蓝色，黑二维码颜色
    @PostMapping("api/code/getcode")
    public String createCode(String lat2,String lng2){
        //创建一个二维码对象
         String url = "http://192.168.101.14:9002/api/ercode/getSign";
         String time = sdf.format(new Date());
        Code code1 = Code.builder().width(300).
                hight(300)
                .content(url+"?lat2="+lat2+"&lng2="+lng2+"&sign="+time)
                .format("png")
                .type("用户")
                .onColor(0x000000)
                .offColor(0xFFFFFFFF)
                .build();
        CreateQRCode code = new CreateQRCode();
        String link = code.createQRCode(code1,lat2,lng2,time);
        return link;
    }

    @GetMapping("/api/ercode/getSign")
    public HashMap<String,Object> getUserdata(@RequestParam String lat2,@RequestParam String lng2,@RequestParam String sign){
        float x = Float.parseFloat(lat2);
        float y = Float.parseFloat(lng2);
        HashMap<String,Object> map = new HashMap<>();
        System.out.println("扫码成功!");
        try{
            map.put("x",x);
            map.put("y",y);
            map.put("createtime",sign);
            map.put("msg","success");
            map.put("code",0);
        }catch (Exception e){
            map.put("msg","error");
            map.put("code",-1);
        }
        return map;
    }

}
