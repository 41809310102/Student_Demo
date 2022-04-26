package com.mySen.demo.model;

import com.mySen.demo.util.Createtableutil.Createtable;

import java.sql.SQLException;

public class Myquestion {
    private int id;
    private String title; //问卷主题
    private String myname;
    private String major;
    private String asktime;
    private String value;
    private String questid;
    private String createusername; //发起人


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Myquestion u = new Myquestion();
        Createtable t = new Createtable();
        t.createTableUtils(u,"t_myquestion");
    }
}
