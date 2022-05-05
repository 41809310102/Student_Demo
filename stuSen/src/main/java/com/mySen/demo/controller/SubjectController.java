package com.mySen.demo.controller;

import com.mySen.demo.model.Sign;
import com.mySen.demo.model.Subject;
import com.mySen.demo.service.IsSignservice;
import com.mySen.demo.service.IsSubjectservice;
import com.mySen.demo.util.excl.Pushtest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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


    // 将 yml 中的自定义配置注入到这里
    @Value("${gorit.file.root.path}")
    private String filePath;
    private Logger log = LoggerFactory.getLogger("SubjectController");
    @PostMapping(value = "api/subject/upload")
    public String uploadfile(@RequestParam("file") MultipartFile file) throws Exception{

        // 得到格式化后的日期
        String format = sdf.format(new Date());
        // 获取上传的文件名称
        String fileName = file.getOriginalFilename();
        // 时间 和 日期拼接
        String newFileName = format + "_" + fileName;
        // 得到文件保存的位置以及新文件名
        File dest = new File(filePath + newFileName);

        InputStream inputStream = file.getInputStream();
        List<Subject> list = new LinkedList<>();
        try {
            // 上传的文件被保存了
            file.transferTo(dest);
            // 打印日志
            log.info("上传成功，当前上传的文件保存在 {}",filePath + newFileName);
            // 自定义返回的统一的 JSON 格式的数据，可以直接返回这个字符串也是可以的。
            System.out.println("导入成功,正在解析！");
            //开始解析，存储在数据库中
            Pushtest pushtest = new Pushtest();
            list = pushtest.uploadExcel(file,inputStream);
            for(int i = 0;i<list.size();i++){
                 subjectservice.addSubjectinfo(list.get(i));
            }
            System.out.println("题目导入完成！");
            inputStream.close();
        } catch (IOException e) {
            log.error(e.toString());
        }
        return "导入成功";
    }
}
