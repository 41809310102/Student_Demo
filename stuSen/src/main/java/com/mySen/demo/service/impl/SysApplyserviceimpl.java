package com.mySen.demo.service.impl;

import com.mySen.demo.dao.ApplyMapper;
import com.mySen.demo.model.Apply;
import com.mySen.demo.service.IsApplyservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysApplyserviceimpl implements IsApplyservice {
    @Autowired
    private ApplyMapper mapper;
    @Override
    public int addapply(Apply apply) {
        return mapper.addapply(apply);
    }

    @Override
    public List<Apply> selectApplyofid(int id) {
        return mapper.selectApplyofid(id);
    }

    @Override
    public int delApply(int id) {
        return mapper.delApply(id);
    }

    @Override
    public int updatestate(int id, String state) {
        return mapper.updatestate(id,state);
    }
}
