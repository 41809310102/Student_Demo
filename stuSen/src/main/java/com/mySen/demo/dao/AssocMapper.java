package com.mySen.demo.dao;

import com.mySen.demo.model.Association;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssocMapper {


    public int adAccinfo(Association association);

    public List<Association> selectAcc(); //获取全部社团信息

    public int updateAcc(Association association);

    public int delAcc(int id);
}
