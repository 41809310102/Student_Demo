package com.mySen.demo.service.impl;

import com.mySen.demo.dao.SignMapper;
import com.mySen.demo.dao.SubjectMapper;
import com.mySen.demo.model.Sign;
import com.mySen.demo.model.Subject;
import com.mySen.demo.service.IsSignservice;
import com.mySen.demo.service.IsSubjectservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysSubjectserviceImpl implements IsSubjectservice {
   @Autowired
   private SubjectMapper subjectMapper;


    @Override
    public int addSubjectinfo(Subject sub) {
        return subjectMapper.addSubjectinfo(sub);
    }

    @Override
    public List<Subject> selectSubjectbyid(int id) {
        return subjectMapper.selectSubjectbyid(id);
    }

    @Override
    public List<Subject> selectSubjectbyid(int id, String type, String xid) {
        return subjectMapper.selectSubjectbycheck(id,type,xid);
    }
}
