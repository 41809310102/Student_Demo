package com.mySen.demo.controller;

import com.mySen.demo.model.Sign;
import com.mySen.demo.model.Subject;
import com.mySen.demo.service.IsSignservice;
import com.mySen.demo.service.IsSubjectservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户逻辑
 *
 * @param
 * @return
 */
@CrossOrigin("http://localhost:8080")
@RestController
public class SubjectController {

    @Autowired
    private IsSubjectservice subjectservice;
    /**
     * @param
     * @Desc 成绩数据添加接口
     * @return map
     */
    @PostMapping("api/subject/addsubject")
    public Map<String,Object> addsubject(@RequestBody Subject subject){
        Map<String,Object> map = new HashMap<>();
        try{
            int i = subjectservice.addSubjectinfo(subject);
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
     * @Desc 获取用户的个人成绩报告
     * @return map
     */
    @GetMapping("api/subject/getsubject")
    public  Map<String,Object> getSignlog(@RequestParam int id){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Subject> sub  = subjectservice.selectSubjectbyid(id);
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


    @GetMapping("api/subject/getselectCheck")
    public  Map<String,Object> getselectCheck(@RequestParam int id,@RequestParam String type,@RequestParam String xid){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Subject> sub  = subjectservice.selectSubjectbyid(id,type,xid);
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
}
