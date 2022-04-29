package com.mySen.demo.controller;

import com.alibaba.fastjson.JSON;
import com.mySen.demo.dao.AddLocalMapper;
import com.mySen.demo.model.Local;
import com.mySen.demo.model.Sign;
import com.mySen.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin("http://localhost:8080")
@RestController
public class LocalController {
    @Autowired
    private AddLocalMapper addLocalMapper;

    /**
     * @param
     * @Desc 添加位置打卡库
     * @return map
     */
    @PostMapping("api/Local/addlocal")
    public Map<String,Object> addUser(@RequestBody Local local){
        Map<String,Object> map = new HashMap<>();
        try{
            int i =addLocalMapper.addLocal(local);
            if(i>0){
                map.put("code",1);
                map.put("msg","添加成功!");
            }else{
                map.put("code",0);
                map.put("msg","添加失败!");
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
     * @Desc 获取可签到的位置
     * @return map
     */
    @GetMapping("api/Local/getAlllocal")
    public  Map<String,Object> selectLocal(){
        Map<String,Object> map = new HashMap<>();
      try{
          List<Local> list = addLocalMapper.seleclaAllLocal();
          map.put("data",list);
          map.put("msg","获取成功");
          map.put("code",1);
      }catch (Exception e){
          System.out.println(e.toString());
          map.put("code",-1);
          map.put("msg","服务器错误！");
      }
      return map;
    }


}
