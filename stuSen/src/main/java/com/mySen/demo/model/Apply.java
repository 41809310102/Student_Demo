package com.mySen.demo.model;

import com.mySen.demo.util.Createtableutil.Createtable;
import lombok.Data;

import java.sql.SQLException;

@Data
public class Apply {
    private int id;
    private int aid;
    private int uid;
    private String starttime;
    private String endtime;
    private String title;
    private String jifen; //积分u
    private int  limitnum;//人数限制
    private String actionuser; //活动发起人
    private String state;//处理状态

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Apply  u = new Apply();
        Createtable t = new Createtable();
        t.createinsertTable(t.getClassdataName(u),"t_apply");
    }

}
