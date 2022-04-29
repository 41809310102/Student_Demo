package com.mySen.demo.model;

import com.mySen.demo.util.Createtableutil.Createtable;
import lombok.Data;

import java.sql.SQLException;
@Data
/**
 * Desc:打卡位置创建类
 * */
public class Local {
    private int id;
    private String lat2;
    private String lng2;
    private String localname;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Local u = new Local();
        Createtable t = new Createtable();
        t.createinsertTable(t.getClassdataName(u),"t_local");
    }
}
