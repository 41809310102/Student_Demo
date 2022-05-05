package com.mySen.demo.model;

import com.mySen.demo.util.Createtableutil.Createtable;
import lombok.Data;

import java.sql.SQLException;
/**
 * Desc:班费类
 * */
@Data
public class Classfee {
    private int id;
    private String name;
    private String addusername;
    private String major;
    private String time;
    private String bz;//备注
    private float money;


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Classfee u = new Classfee();
        Createtable t = new Createtable();
        t.createupdateTable(t.getClassdataName(u),"t_classfee");
    }
}
