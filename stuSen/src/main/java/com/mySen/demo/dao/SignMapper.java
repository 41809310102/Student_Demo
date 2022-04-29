package com.mySen.demo.dao;

import com.mySen.demo.model.Sign;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SignMapper {
    public int addSigninfo(Sign sign);
    public List<Sign> selectSign(int uid);
    public int selectCount(String uname,int id);
    public List<Sign> selectAllsign(String uname,int id, int pageStart, int pageSize);
    public int deleteSign(int id);
}
