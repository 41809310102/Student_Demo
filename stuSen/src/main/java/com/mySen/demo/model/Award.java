package com.mySen.demo.model;

import com.mySen.demo.util.Createtableutil.Createtable;
import lombok.Data;

import java.sql.SQLException;

/**
 * Desc:获奖类
 * */
@Data
public class Award {
    private int id;
    private int uid;
    private String gettime;
    private String getgrade;//奖励分数
    private String level; //奖励等级
    private String CreateTime;//创建时间
    private String major;//专业
    private String cardid;//学号
    private String grade;//年级


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Award  u = new Award();
        Createtable t = new Createtable();
        t.createTableUtils(u,"t_award");
    }}
