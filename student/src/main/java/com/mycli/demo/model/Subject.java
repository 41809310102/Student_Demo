package com.mycli.demo.model;

import com.mycli.demo.util.Createtableutil.Createtable;

import java.sql.SQLException;

public class Subject {
    private int id;
    private int uid;
    private String Subjectname;
    private int grade;
    private int teacherid;
    private String type;
    private String xid;//学期

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Subject u = new Subject();
        Createtable t = new Createtable();
        t.createTableUtils(u,"t_subject");
    }
}