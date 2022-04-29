package com.mySen.demo.controller;

import com.mySen.demo.dao.ClassfeeMapper;
import com.mySen.demo.model.Classfee;
import com.mySen.demo.model.Pienode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
@CrossOrigin("http://localhost:8080")
@RestController
public class EchartsController {
    @Autowired
    private ClassfeeMapper mapper;
    /**
     * @param
     * @Desc 班级班费支出，收入表
     * @return map
     */
    @GetMapping("api/Echart/getallClass")
    public Map<String,Object> getallClass(@RequestParam String major){
        Map<String,Object> map = new HashMap<>();
        float a = 0;
        float b = 0;
        try{
            List<Classfee> log =mapper.getAllclassfee(major);
            for(int i = 0;i<log.size();i++){
                if(log.get(i).getMoney()<0){
                    b=b+log.get(i).getMoney();
                }else{
                    a=a+log.get(i).getMoney();
                }
            }
            List<Pienode> list = new LinkedList<>();
            Pienode node = new Pienode();
            node.setName("班费收入");
            node.setValue(a);
            Pienode node1 = new Pienode();
            node1.setName("班费支出");
            node1.setValue(b*-1);
            list.add(node);list.add(node1);
            map.put("data",list);
            map.put("code",1);
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",0);
            map.put("msg","服务器发生错误");
        }
        return map;
    }


}
