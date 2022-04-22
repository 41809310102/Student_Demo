package com.mySen.demo.dao;

import com.mySen.demo.model.Sign;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SignMapper {
    public int addSigninfo(Sign sign);
    public List<Sign> selectSign(int uid);
}
