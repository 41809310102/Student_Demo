package com.mySen.demo.service.impl;

import com.mySen.demo.dao.ActionMapper;
import com.mySen.demo.model.Action;
import com.mySen.demo.service.IsActionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysActionserviceImpl implements IsActionservice {
    @Autowired
    private ActionMapper mapper;

    @Override
    public int addActioninfo(Action action) {
        return mapper.addActioninfo(action);
    }

    @Override
    public List<Action> selectActiontbyid(int id) {
        return mapper.selectActiontbyid(id);
    }

    @Override
    public List<Action> selectActiontall() {
        return mapper.selectActiontall();
    }

    @Override
    public List<Action> selectActionbycheck(int id, String title) {
        return mapper.selectActionbycheck(id,title);
    }

    @Override
    public int updateAction(Action action) {
        return mapper.updateAction(action);
    }

    @Override
    public int deleteAction(int id) {
        return mapper.deleteAction(id);
    }
}
