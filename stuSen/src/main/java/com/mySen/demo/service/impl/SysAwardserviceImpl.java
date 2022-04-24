package com.mySen.demo.service.impl;

import com.mySen.demo.dao.AwardMapper;
import com.mySen.demo.model.Award;
import com.mySen.demo.model.Subject;
import com.mySen.demo.service.IsAwardservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysAwardserviceImpl implements IsAwardservice {
    @Autowired
    private AwardMapper mapper;

    @Override
    public int addAwardinfo(Award  award) {
        return mapper.addAwardinfo(award);
    }

    @Override
    public List<Award> selectAwardtbyid(int id) {
        return mapper.selectAwardtbyid(id);
    }

    @Override
    public List<Award> selectAwardbycheck(int id, String aname) {
        return mapper.selectAwardbycheck(id,aname);
    }

    @Override
    public int uploadpic(int uid,String pic) {
        return mapper.uploadpic(uid,pic);
    }
}
