package com.mycli.demo.model;

import com.mycli.demo.util.Createtableutil.Createtable;
import lombok.Data;

import java.sql.SQLException;

@Data
public class User {
    private int id;
    private int vid; //奖励编号
    private String username;
    private String password;
    private String major;
    private String mygroup;
    private String cardid;
    private String grade; //学分
    private String hobby;//爱好
    private String association;//社团
    private int isdelect;//冻结标记
    private String role ;//身份标记

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        User u = new User();
        Createtable t = new Createtable();
        t.createTableUtils(u,"t_student");
    }
}
