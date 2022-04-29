package com.mySen.demo.controller;

import com.mySen.demo.model.Codelocal;
import com.mySen.demo.util.ercode.Code;
import com.mySen.demo.util.ercode.CreateQRCode;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
@CrossOrigin("http://localhost:8080")
@RestController
public class Createcode {

    /**
     * @desc:专属二维码
     * */
    private List<String> queue = new LinkedList<>();
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private  int Color[]= {0xFF00FF00,0x0000FF,0x000000};//分别保存为绿色，蓝色，黑二维码颜色
    @PostMapping("api/code/getcode")
    public String createCode(@RequestBody Codelocal codes){
        //创建一个二维码对象
         String url = "http://192.168.101.14:9002/api/ercode/getSign";
         String time = sdf.format(new Date());
        Code code1 = Code.builder().width(300).
                hight(300)
                .content(url+"?lat2="+codes.getLat2()+"&lng2="+codes.getLng2()+"&sign="+time+"&actionid="+codes.getActionid())
                .format("png")
                .type("用户")
                .onColor(0x000000)
                .offColor(0xFFFFFFFF)
                .build();
        CreateQRCode code = new CreateQRCode();
        String link = code.createQRCode(code1,codes.getLat2(),codes.getLng2(),time);
        //放入队列中
        queue.add(link);
        return link;
    }

    @GetMapping("/api/ercode/getSign")
    public HashMap<String,Object> getUserdata(@RequestParam String lat2,@RequestParam String lng2,@RequestParam String sign
    ,@RequestParam int actionid){
        float x = Float.parseFloat(lat2);
        float y = Float.parseFloat(lng2);
        HashMap<String,Object> map = new HashMap<>();
        System.out.println("扫码成功!");
        try{
            map.put("x",x);
            map.put("y",y);
            map.put("createtime",sign);
            map.put("actionid",actionid);
            map.put("msg","success");
            map.put("code",0);
        }catch (Exception e){
            map.put("msg","error");
            map.put("code",-1);
        }
        return map;
    }

    @PostMapping("/api/ercode/getCodeUrl")
    public String getcodeurl(){
        int i = queue.size();
        if(queue.size()>0){
            return "\""+queue.get(i-1)+"\""; //每次获取最新二维码连接
        }else{
            return "error";
        }
    }
}
