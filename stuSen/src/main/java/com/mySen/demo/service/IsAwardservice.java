package com.mySen.demo.service;

import com.mySen.demo.model.Award;
import com.mySen.demo.model.Subject;

import java.util.List;

public interface IsAwardservice {
    /**
     * 个人奖励
     *
     * @param
     * @return 数据库处理
     */

    public int addAwardinfo(Award award);

    public List<Award> selectAwardtbyid(int id);

    public List<Award> selectAwardbycheck(int id,String aname);

    public int uploadpic(int uid,String pic);

    public int updateAwardofid(Award award);

    public int deleteAwerdofid(int id);
}
