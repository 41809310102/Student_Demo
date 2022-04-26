package com.mySen.demo.model;

import com.mySen.demo.util.Createtableutil.Createtable;
import lombok.Data;

import java.sql.SQLException;

/**
 * Desc:问卷类
 * */
@Data
public class Paper {
    private int id;
    private String questid; //问卷id
    private String major; //目标班级
    private String username; //创建人
    private String time;//创建时间
    private String title; //问卷主题
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Paper u = new Paper();
        Createtable t = new Createtable();
        t.createinsertTable(t.getClassdataName(u),"t_paper");
    }
}
