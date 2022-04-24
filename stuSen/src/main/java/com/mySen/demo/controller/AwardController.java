package com.mySen.demo.controller;

import com.mySen.demo.model.Award;
import com.mySen.demo.model.Subject;
import com.mySen.demo.service.IsAwardservice;
import com.mySen.demo.service.IsSubjectservice;
import com.mySen.demo.util.OBSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户逻辑
 *
 * @param
 * @return
 */
@CrossOrigin("http://localhost:8081")
@RestController
public class AwardController {

    @Autowired
    private IsAwardservice isAwardservice;
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * @param
     * @Desc 奖励数据添加接口
     * @return map
     */
    @PostMapping("api/Award/addsubject")
    public Map<String,Object> addAward(@RequestBody Award award){
        Map<String,Object> map = new HashMap<>();
        try{
            award.setCreatetime(sdf.format(new Date()));
            int i =isAwardservice.addAwardinfo(award);
            if(i>0){
                map.put("code",1);
                map.put("msg","添加成功");
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
     * @Desc 获取用户的个人奖励报告
     * @return map
     */
    @GetMapping("api/Award/getmyAwardofid")
    public  Map<String,Object> getmyAwardofid(@RequestParam int id){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Award> sub  = isAwardservice.selectAwardtbyid(id);
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
     * @Desc 按奖励名字搜索用户的个人奖励
     * @return map
     */

    @GetMapping("api/Award/getselectAwardCheck")
    public  Map<String,Object> getselectAwardCheck1(@RequestParam int id,@RequestParam String aname){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Award> sub  = isAwardservice.selectAwardbycheck(id,"%"+aname+"%");
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
     * @Desc 获取用户的个人奖励报告(保存在本地)
     * @return map
     */
    @PostMapping("api/Award/uploadAwardpics")
    public  String uploadAwardpics(@RequestParam(value = "file") MultipartFile file,@RequestParam int id) throws IOException {
        String type = file.getOriginalFilename();
        StringBuffer str = new StringBuffer();
        str.append(id);
        String picname = str.toString()+type;
        System.out.println(file.getSize());
        InputStream o =  file.getInputStream();
       // String path = "F:\\Github\\Student_Demo\\pic";
        String path ="";
        OBSUtils obs = new OBSUtils();//调用华为obs对象存储服务
        try {
            String key =picname;//以用户姓名+openid后5为key
            obs.ObsUpload("myfacepic",key,o);
            path = obs.getLink(key);
            o.close();
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

     //保存文件
    private void savePic(InputStream inputStream,String path, String fileName) {

        OutputStream os = null;
        try {
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
