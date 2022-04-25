package com.mySen.demo.model;

import com.mySen.demo.util.Createtableutil.Createtable;
import lombok.Data;

import java.sql.SQLException;

/**
 * Desc:投票类
 * */
@Data
public class Vote {
    private  int id;
    private  String name; //发起人
    private  String major; //所属班级
    private  String question;//问题
    private  String options; //将问题转为字符串存储在数据中
    private  String[] children; //选项问题
    private  int a;
    private  int b;
    private  int c;
    private  int d;
    private  String endtime; //截止时间
    private  Boolean isout;//过期
    private  Boolean ispass;//投票记录



    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Vote u = new Vote();
        Createtable t = new Createtable();
        t.createinsertTable(t.getClassdataName(u),"t_vote");
    }
}
