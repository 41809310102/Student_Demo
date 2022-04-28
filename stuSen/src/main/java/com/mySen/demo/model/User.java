package com.mySen.demo.model;

import com.mySen.demo.util.Createtableutil.Createtable;
import lombok.Data;

import java.sql.SQLException;
/**
 * Desc:人员类
 * */
@Data
public class User {
    private int id;
    private int vid; //奖励编号
    private String pic;//头像
    private String phone;
    private String username;
    private String password;
    private String major;
    private String shool_group;
    private String cardid;
    private String sex;
    private String desction;
    private String age;
    private float grade; //积分
    private String hobby;//爱好
    private String association;//社团
    private int isdelect;//冻结标记
    private String role ;//身份标记

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        User u = new User();
        Createtable t = new Createtable();

    }
}
