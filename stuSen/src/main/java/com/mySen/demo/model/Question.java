package com.mySen.demo.model;

import com.mySen.demo.util.Createtableutil.Createtable;
import lombok.Data;

import java.sql.SQLException;
import java.util.List;
/**
 * Desc:问卷子题类
 * */
@Data
public class Question {
    private int id;
    private String title; //问卷主题
    private String questid;  //所属问卷id
    private String username;
    private String major;
    private String type;
    private String questionName;
    private List<Answers> answers;
    private byte[]  answersopt; //从数据库获得序列化字符串
   public static void main(String[] args) throws SQLException, ClassNotFoundException {
       Question u = new Question();
       Createtable t = new Createtable();
       t.createinsertTable(t.getClassdataName(u),"t_question");
   }
}
