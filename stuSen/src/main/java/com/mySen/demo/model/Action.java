package com.mySen.demo.model;

import com.mySen.demo.util.Createtableutil.Createtable;
import lombok.Data;

import java.sql.SQLException;

@Data
public class Action {
    private int id;
    private String actiontime;
    private String starttime;
    private String endtime;
    private String title;
    private String jifen; //积分
    private int  limitnum;//人数限制
    private String actionUser; //活动发起人
    private String state; //当前状态,控制删除逻辑
    private Boolean isapply;//当前用户是否申请

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Action u = new Action();
        Createtable t = new Createtable();
        t.createupdateTable(t.getClassdataName(u),"t_action");
    }
}
