package com.mySen.demo.model;

import com.mySen.demo.util.Createtableutil.Createtable;
import lombok.Data;

import java.sql.SQLException;

/**
 * Desc:社团类
 * */
@Data
public class Association{
    private  int id;
    private String name; //社团名称
    private String createuser; //创建人
    private String desction;//社团描述

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Association u = new Association();
        Createtable t = new Createtable();
        t.createupdateTable(t.getClassdataName(u),"t_association");
    }
}
