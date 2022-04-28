package com.mySen.demo.controller;

import com.mySen.demo.dao.AssocMapper;
import com.mySen.demo.model.Association;
import com.mySen.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AssController {

    @Autowired
    public AssocMapper mapper;

    /**
     * @param
     * @Desc 社团添加接口逻辑
     * @return map
     */
    @PostMapping("api/association/addAss")
    public Map<String,Object> addAss(@RequestBody Association  association){
        Map<String,Object> map = new HashMap<>();
        try{
            int i = mapper.adAccinfo(association);
            if(i>0){
                map.put("code",1);
                map.put("msg","社团添加成功!");
            }else{
                map.put("code",0);
                map.put("msg","社团添加失败!");
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
     * @Desc 社团查找逻辑
     * @return map
     */
    @PostMapping("api/association/SelectAss")
    public Map<String,Object> selectAssoc(){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Association> list = mapper.selectAcc();
                map.put("data",list);
                map.put("code",0);
                map.put("msg","社团添加失败!");
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器错误！");
        }
        return map;
    }


    /**
     * @param
     * @Desc 社团信息修改逻辑
     * @return map
     */
    @PostMapping("api/association/updateAss")
    public Map<String,Object> updateAss(@RequestBody Association  association){
        Map<String,Object> map = new HashMap<>();
        try{
            int i = mapper.updateAcc(association);
            if(i>0){
                map.put("code",1);
                map.put("msg","社团信息修改成功!");
            }else{
                map.put("code",0);
                map.put("msg","社团信息修改失败!");
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
     * @Desc 社团删除接口
     * @return map
     */
    @PostMapping("api/association/delSelect")
    public Map<String,Object> delAssoc(@RequestParam int id){
        Map<String,Object> map = new HashMap<>();
        try{
            int i = mapper.delAcc(id);
            if(i>0){
                map.put("code",1);
                map.put("msg","社团信息删除成功!");
            }else{
                map.put("code",0);
                map.put("msg","社团信息删除失败!");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器错误！");
        }
        return map;
    }

}
