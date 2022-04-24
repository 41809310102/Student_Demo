package com.mySen.demo.service;

import com.mySen.demo.model.Action;
import com.mySen.demo.model.Award;

import java.util.List;

public interface IsActionservice {
    /**
     * 活动逻辑
     *
     * @param
     * @return 数据库处理
     */

    public int addActioninfo(Action action);

    public List<Action> selectActiontbyid(int id);

    public List<Action> selectActiontall();

    public List<Action> selectActionbycheck(int id, String title);

    public int updateAction(Action action);

    public int deleteAction(int id);

}
