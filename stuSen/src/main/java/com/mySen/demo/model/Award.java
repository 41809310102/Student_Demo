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
    private String gettime;//奖励获取时间
    private String pic;//奖励图片地址
    private String aname;//奖励名字
    private String getgrade;//奖励分数
    private String level; //奖励等级
    private String createtime;//创建时间
    private String username;//用户姓名
    private String cardid;//学号

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Award  u = new Award();
        Createtable t = new Createtable();
        t.createinsertTable(t.getClassdataName(u),"t_award");
    }}
