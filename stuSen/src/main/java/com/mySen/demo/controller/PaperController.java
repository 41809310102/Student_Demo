package com.mySen.demo.controller;

import com.alibaba.fastjson.JSON;
import com.mySen.demo.dao.PaperMapper;
import com.mySen.demo.dao.QuestionMapper;
import com.mySen.demo.model.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin("http://localhost:8080")
@RestController
public class PaperController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private PaperMapper paperMapper;
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @param
     * @Desc 添加问卷
     * @return map
     */
    @PostMapping("api/paper/addPaper")
    public  Map<String,Object> addPaper(@RequestBody  List<Question> topic) throws Exception {
        int i = 0;
        String code = "";
        Random random = new Random();
        for (int j = 0; j < 6; j++) {

            int r = random.nextInt(10); //每次随机出一个数字（0-9）,生成问卷id与子题绑定

            code = code + r;  //把每次随机出的数字拼在一起

        }
        if(topic.size()>0){
            Paper paper = new Paper();
            paper.setQuestid(code);
            paper.setMajor(topic.get(0).getMajor());
            paper.setUsername(topic.get(0).getUsername());
            paper.setTime(sdf.format(new Date()));
            paper.setTitle(topic.get(0).getTitle());
            for(Question question:topic){
                question.setAnswersopt(obj2byte(question.getAnswers()));
                question.setQuestid(code); //绑定问卷id
                i  = questionMapper.addquestion(question);
            }
              i = paperMapper.addPaper(paper);
        }
        Map<String,Object> map = new HashMap<>();

        if(i>0){
             map.put("msg","问卷创建成功!");
             map.put("code",1);
         }else{
             map.put("msg","问卷创建失败!");
             map.put("code",-1);
         }
        return map;
    }

    /**
     * @param
     * @Desc 获取问卷列表
     * @return map
     */
    @GetMapping("api/paper/getPaperfarther")
    public Map<String,Object> getPaperfarther(@RequestParam String major){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Paper> res = new LinkedList<>();//保存没有回答的问卷
            List<Paper> res1 = new LinkedList<>();//保存已经回答的问卷
            List<Paper> papers = paperMapper.getPaper(major);
            List<Myquestion> log = paperMapper.getlog(major,"胡俊杰");
            System.out.println(log.toString());
           for(int i = 0;i<log.size();i++){
               for(int j = 0;j<papers.size();j++){
                   if(papers.get(j).getQuestid().equals(log.get(i).getQuestid())){
                      res1.add(papers.get(j));
                       System.out.println(res1.toString());
                      papers.remove(j);
                   }
               }
           }
            map.put("data",papers);
            map.put("data1",res1);
            map.put("code",1);
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器发生错误");
        }
        return map;

    }

    /**
     * @param
     * @Desc 获取问卷子题
     * @return map
     */
    @GetMapping("api/paper/getPaper")
    public  Map<String,Object> getPaperchild(@RequestParam String questid) throws Exception {
      List<Question> list = questionMapper.getQuestionofid(questid);
      List<Question> res = new LinkedList<>();
      for(Question question : list){
          question.setAnswers(byte2obj(question.getAnswersopt()));
          res.add(question);
      }
      Map<String,Object> map = new HashMap<>();
      map.put("code",1);
      map.put("data",res);
      return map;
    }
    /**
     * @param
     * @Desc 提交问卷回答
     * @return map
     */
    @GetMapping("api/paper/uploadmyask")
     public String getMyquestion(@RequestParam String[] myoption,@RequestParam String questid,
                                 @RequestParam String major,@RequestParam String myname,@RequestParam String title){
        String str= "";
        for(int i = 0;i<myoption.length;i++){
            int id = i+1;
            str=str+id+"."+myoption[i]+" ";
        }
        System.out.println(myoption);
        Myquestion myquestion = new Myquestion();
        myquestion.setValue(str);
        myquestion.setMajor(major);myquestion.setQuestid(questid);myquestion.setMyname(myname);
        myquestion.setTitle(title);myquestion.setAsktime(sdf.format(new Date()));
        System.out.println(myquestion.toString());
        int i =  paperMapper.addMyquestion(myquestion);
        return i>0?"success":"error";
     }


    //序列化对象
    public  byte[] obj2byte(Object obj) throws Exception {
        byte[] ret = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(obj);
        out.close();
        ret = baos.toByteArray();
        baos.close();
        System.out.println(ret);
        return ret;
    }

    //反序列化
    public List<Answers> byte2obj(byte[] bytes) throws Exception {
        List<Answers> ret = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bais);
        ret = (List<Answers>) in.readObject();
        in.close();
        return ret;
    }
}
