package com.mySen.demo.controller;

import com.mySen.demo.dao.ClassfeeMapper;
import com.mySen.demo.model.Classfee;
import com.mySen.demo.model.Sign;
import com.mySen.demo.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:8081")
@RestController
public class ClassfeeController {
    @Autowired
    private ClassfeeMapper mapper;
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * @param
     * @Desc 报账添加
     * @return map
     */

    @PostMapping("api/classfee/addclassfee")
    public Map<String,Object> addClassfee(@RequestBody Classfee classfee){
        Map<String,Object> map = new HashMap<>();
        try{
            classfee.setTime(sdf.format(new Date()));
            int i =mapper.addClassfeeinfo(classfee);
            if(i>0){
                map.put("code",1);
                map.put("msg","成绩添加成功");
            }else{
                map.put("code",0);
                map.put("msg","成绩添加失败!");
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
     * @Desc 获取用户班级的报账信息
     * @return map
     */
    @GetMapping("api/Classfee/getallClass")
    public  Map<String,Object> getallClass(@RequestParam String major){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Classfee> log =mapper.getAllclassfee(major);
            map.put("data",log);
            map.put("code",1);
            map.put("msg","刷新成功!");
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",0);
            map.put("msg","服务器发生错误");
        }
        return map;
    }


    /**
     * @param
     * @Desc 报账修改
     * @return map
     */
    @PostMapping("api/classfee/updateclassfee")
    public Map<String,Object> updateClassfee(@RequestBody Classfee classfee){
        Map<String,Object> map = new HashMap<>();
        try{
            classfee.setTime(sdf.format(new Date()));
            int i =mapper.updateClassfeeofid(classfee);
            if(i>0){
                map.put("code",1);
                map.put("msg","记录修改成功");
            }else{
                map.put("code",0);
                map.put("msg","记录修改失败!");
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
     * @Desc 报账修改
     * @return map
     */
    @GetMapping("api/classfee/delclassfee")
    public Map<String,Object> delclassfee(@RequestParam int id){
        Map<String,Object> map = new HashMap<>();
        try{
            int i =mapper.delclassfee(id);
            if(i>0){
                map.put("code",1);
                map.put("msg","记录删除成功");
            }else{
                map.put("code",0);
                map.put("msg","记录删除失败!");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器错误！");
        }
        return map;
    }
}
