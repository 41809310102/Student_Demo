package com.mySen.demo.controller;

import com.alibaba.fastjson.JSON;
import com.mySen.demo.dao.UserMapper;
import com.mySen.demo.model.Action;
import com.mySen.demo.model.Pienode;
import com.mySen.demo.model.QueryInfo;
import com.mySen.demo.model.User;
import com.mySen.demo.service.IsUserservice;
import com.mySen.demo.util.OBSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;
import static java.lang.System.setOut;

/**
 * 用户逻辑
 *
 * @param
 * @return
 */
@CrossOrigin("http://localhost:8080")
@RestController
public class UserController {

    @Autowired
    private IsUserservice isUserservice;
    @Autowired
    private UserMapper userMapper;
    /**
     * @param user 用户信息
     * @Desc 用户注册
     * @return map
     */
    @PostMapping("api/user/adduser")
    public Map<String,Object> addUser(@RequestBody  User user){
        System.out.println(user.toString());
        Map<String,Object> map = new HashMap<>();
        try{
            user.setIsdelect(0);
            user.setRole("普通学生");
            int i = isUserservice.addUser(user);
            if(i>0){
                map.put("code",1);
                map.put("msg","注册成功!");
            }else{
                map.put("code",0);
                map.put("msg","注册失败!");
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
    @PostMapping("api/user/loginuser")
    public Map<String,Object> loginUser(@RequestBody User users){
        Map<String,Object> map = new HashMap<>();
        try{
            User user = isUserservice.selectUser(users.getUsername(),users.getPassword());
            if(user==null){
                map.put("code",-2);
                map.put("msg","账号或密码错误，请重新登录");
                return map;
            }
            if(user.getIsdelect()==1){
                map.put("code",0);
                map.put("msg","用户账号已经被冻结");
            }else{
                map.put("code",1);
                map.put("user",user);
                map.put("msg","登录成功");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器错误！");
        }
        return map;
    }



    @PostMapping("api/user/updateUser")
    private String updateofUser(@RequestBody User user){
         System.out.println(user.toString());
         int i = isUserservice.updateUserofid(user);
         return i>0? "success":"error";
    }
    //上传头像接口
    @RequestMapping("api/user/updateUserpic")
    private String updateofUserpic(@RequestParam(value = "file") MultipartFile file,@RequestParam int id) throws IOException {
        java.io.File store = null;  //目的文件
        try {
            //存在每个用户有一个自己名字命名的文件夹
            store = new java.io.File(java.io.File.separator + "userheadpic", file.getOriginalFilename());
        } catch (Exception e) {
            return "error";
        }
        long size = file.getSize();  //上传文件的大小
        //将文件存入obs
        OBSUtils obs = new OBSUtils();
        String Key = "hjj"+file.getOriginalFilename()+".jpg";
        //  out.println(Key);
        InputStream inputStream = file.getInputStream();
        obs.ObsUpload("myfacepic", Key, inputStream);
        String link = obs.getLink(Key);
        isUserservice.updateofheadpic(link,id);
        return link;
    }

    @GetMapping("api/user/selectUserbyid")
    public  String selectUserbyid(@RequestParam int id){
        User user = isUserservice.selectUserbyid(id);
        if(user==null){
            return "error";
        }else{
            return JSON.toJSONString(user);
        }
    }


    /**
     * @param
     * @Desc 管理员获取学生信息表
     * @return map
     */
    @PostMapping("api/User/getUserlist")
    public  Map<String,Object> getUserlist(@RequestBody QueryInfo queryInfo){
        int numbers =userMapper.getUserCounts("%"+queryInfo.getQuery()+"%");
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        List<User> applys =userMapper.getAllUser("%"+queryInfo.getQuery()+"%",pageStart,queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("number",numbers);
        res.put("data",applys);
        res.put("code",1);
        return res;
    }

    /**
     * @param
     * @Desc 获取当前用户积分排行榜
     * @return map
     */

    @PostMapping("api/user/gradetop")
    public Map<String,Object> getGradeTop(){
        Map<String,Object> map = new HashMap<>();
        try{
            List<User> list = isUserservice.getGradeTop();
            if(list.size()>0){
                map.put("data",list);
                map.put("code",1);
                map.put("msg","排行获取成功");
                map.put("number",list.size());
            }else{
                map.put("data",list);
                map.put("code",0);
                map.put("msg","当前尚没有学生进入排行榜");
            }
        }catch (Exception e){
            map.put("code",-1);
            map.put("msg","服务器好像出现问题");
            out.println(e.toString());
        }
        return map;
    }


    /**
     * @param
     * @Desc 获取当前用户积分排行榜
     * @return map
     */

    @PostMapping("api/user/getCross")
    public Map<String,Object> getGradeCrose(){
        Map<String,Object> map = new HashMap<>();
        List<Pienode> lists = new LinkedList<>();
        try{
            List<User> list = isUserservice.getAlluser();
            int a = 0; //保存积分通过的人
            if(list.size()>0){
              for(User user : list){
                  if(user.getGrade()>6){
                      a+=1;
                  }
              }
              int b  = list.size()-a;
              Pienode pienode = new Pienode(); pienode.setValue(a); pienode.setName("当前积分合格人数");
              Pienode pienode1 = new Pienode(); pienode1.setName("当前积分不合格人数");pienode1.setValue(b);
              lists.add(pienode); lists.add(pienode1);
                map.put("data",lists);
                map.put("code",0);
                map.put("msg","当前数据获取成功");
            }else{
                map.put("data",lists);
                map.put("code",0);
                map.put("msg","当前数据为空");
            }
        }catch (Exception e){
            map.put("code",-1);
            map.put("msg","服务器好像出现问题");
            out.println(e.toString());
        }
        return map;
    }


}
