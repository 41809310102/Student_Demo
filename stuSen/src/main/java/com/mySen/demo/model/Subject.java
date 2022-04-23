package com.mySen.demo.model;

import com.mySen.demo.util.Createtableutil.Createtable;
import lombok.Data;

import java.sql.SQLException;
/**
 * Desc:成绩类
 * */
@Data
public class Subject {
    private int id;
    private int uid;
    private String subjectname;
    private String subjecttype;
    private String xuefen;
    private String jidian;
    private String bz;
    private String ispass;
    private String school_group; //学院
    private int grade;
    private int teacherid;
    private String type;
    private String xid;//学期

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Subject u = new Subject();
        Createtable t = new Createtable();
        t.createinsertTable(t.getClassdataName(u),"t_subject");
    }
}
