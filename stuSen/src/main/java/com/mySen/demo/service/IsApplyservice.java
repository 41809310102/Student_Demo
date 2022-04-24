package com.mySen.demo.service;

import com.mySen.demo.model.Apply;

import java.util.List;

public interface IsApplyservice {
    public  int addapply(Apply apply);

    public List<Apply> selectApplyofid(int id);

    public  int delApply(int id);

    public int  updatestate(int id,String state);
}
