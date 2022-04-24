package com.mySen.demo.controller;

import com.mySen.demo.model.Action;
import com.mySen.demo.model.Apply;
import com.mySen.demo.model.Award;
import com.mySen.demo.service.IsApplyservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApplyController {
    @Autowired
    private IsApplyservice applyservice;

    /**
     * @param
     * @Desc 申请活动
     * @return map
     */
    @PostMapping("api/Apply/addapply")
    public Map<String,Object> addapply(@RequestBody Apply apply){
        Map<String,Object> map = new HashMap<>();
        try{
            int i =applyservice.addapply(apply);
            if(i>0){
                map.put("code",1);
                map.put("msg","申请成功");
            }else{
                map.put("code",0);
                map.put("msg","申请失败!");
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
     * @Desc 获取当前申请的活动
     * @return map
     */

    @PostMapping("api/Apply/getapplybyid")
    public Map<String,Object> getapplybyid(@RequestParam int id){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Apply> sub  = applyservice.selectApplyofid(id);
            int numbers = sub.size();
            map.put("data",sub);
            map.put("code",1);
            map.put("numbers",numbers);
            map.put("msg","数据获取成功!");
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器错误！");
        }
        return map;
    }
    /**
     * @param
     * @Desc 管理员修改申请状态
     * @return map
     */

    @GetMapping("api/Apply/updatestate")
    public Map<String,Object> getapplybyid(@RequestParam int id,@RequestParam String state){
        Map<String,Object> map = new HashMap<>();
        try{
            int i =applyservice.updatestate(id,state);
            if(i>0){
                map.put("code",1);
                map.put("msg","操作成功");
            }else{
                map.put("code",0);
                map.put("msg","操作失败");
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
     * @Desc 删除申请
     * @return map
     */

    @GetMapping("api/Apply/delapply")
    public Map<String,Object> delapply(@RequestParam int id){
        Map<String,Object> map = new HashMap<>();
        try{
            int i =applyservice.delApply(id);
            if(i>0){
                map.put("code",1);
                map.put("msg","操作成功");
            }else{
                map.put("code",0);
                map.put("msg","操作失败");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器错误！");
        }
        return map;
    }




}
