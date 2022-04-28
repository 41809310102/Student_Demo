package com.mySen.demo.controller;

import com.alibaba.fastjson.JSON;
import com.mySen.demo.dao.ActionMapper;
import com.mySen.demo.model.Action;
import com.mySen.demo.model.QueryInfo;
import com.mySen.demo.service.IsActionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:8080")
@RestController
public class ActionController {
    @Autowired
    private IsActionservice isActionservice;
    @Autowired
    private ActionMapper actionMapper;
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * @param
     * @Desc 添加活动
     * @return map
     */
    @PostMapping("api/Action/addAction")
    public Map<String,Object> addAction(@RequestBody Action action){
        Map<String,Object> map = new HashMap<>();
        try{
            action.setActiontime(sdf.format(new Date()));
            int i =isActionservice.addActioninfo(action);
            if(i>0){
                map.put("code",1);
                map.put("msg","活动添加成功");
            }else{
                map.put("code",0);
                map.put("msg","活动添加失败!");
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
     * @Desc 获取全部活动信息
     * @return map
     */
    @GetMapping("api/Action/getActionall")
    public  Map<String,Object> getmyActionofall(){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Action> sub  = isActionservice.selectActiontall();
            int numbers = sub.size();
            map.put("data",sub);
            map.put("code",1);
            map.put("numbers",numbers);
            map.put("msg","数据获取成功!");
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器发生错误!");
        }
        return map;
    }


    /**
     * @param
     * @Desc 按活动标题名字搜索活动
     * @return map
     */

    @GetMapping("api/Action/getselectActionCheck")
    public  Map<String,Object> getselectActionCheck1(@RequestParam int id,@RequestParam String title){
        Map<String,Object> map = new HashMap<>();
        try{
            System.out.println(title);
            List<Action> sub  =isActionservice.selectActionbycheck(id,"%"+title+"%");
            int numbers = sub.size();
            map.put("data",sub);
            map.put("code",1);
            map.put("numbers",numbers);
            map.put("msg","数据获取成功!");
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器发生错误!");
        }
        return map;
    }

    /**
     * @param
     * @Desc 管理员获取活动信息()
     * @return map
     */
    @PostMapping("api/Action/getselectPageadmin")
    public  Map<String,Object> getselectPageadmin(@RequestBody QueryInfo queryInfo){
        int numbers =actionMapper.getActionCounts("%"+queryInfo.getQuery()+"%");
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        List<Action> applys =actionMapper.getAllAction("%"+queryInfo.getQuery()+"%",pageStart,queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("number",numbers);
        res.put("data",applys);
        res.put("code",1);
        return res;
    }



    /**
     * @param
     * @Desc 管理员删除活动信息()
     * @return String
     */
    @GetMapping("api/Action/deleteAction")
    public String deleteActionadmin(@RequestParam  int id){
        int i =actionMapper.deleteAction(id);
        return i>0? "success":"error";
    }

    /**
     * @param
     * @Desc 保存修改后的活动信息
     * @return map
     */
    @PostMapping("api/Action/updateAction")
    public Map<String,Object> updateAction(@RequestBody Action action){
        Map<String,Object> map = new HashMap<>();
        try{
            action.setActiontime(sdf.format(new Date()));
            int i =isActionservice.updateAction(action);
            if(i>0){
                map.put("code",1);
                map.put("msg","活动修改成功");
            }else{
                map.put("code",0);
                map.put("msg","活动修改失败!");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器错误！");
        }
        return map;
    }

}
