package com.mySen.demo.service;

import com.mySen.demo.model.Sign;
import com.mySen.demo.model.User;

import java.util.List;

public interface IsSignservice {
    /**
     * 添加用户信息
     *
     * @param
     * @return 数据库处理
     */
    public int  addSign(Sign sign);

    public List<Sign> selectSign(int uid);

    public int selectCount(String uname,int id);
    public List<Sign> selectAllsign(String uname,int id, int pageStart, int pageSize);
    public int deleteSign(int id);
}
