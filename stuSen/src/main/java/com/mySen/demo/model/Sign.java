package com.mySen.demo.model;

import com.mySen.demo.util.Createtableutil.Createtable;
import lombok.Data;

import java.sql.SQLException;

/**
 * Desc:签到类
 * */
@Data
public class Sign {

    private int id;
    private int uid;
    private int aid;//活动id
    private String signtime;
    private String local;
    private String uname;
    private String cardid;
    private String major;
    private String addsigntime;//补卡记录
    private int isdelect;//删除标记

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Sign u = new Sign();
        Createtable t = new Createtable();
    }

}
