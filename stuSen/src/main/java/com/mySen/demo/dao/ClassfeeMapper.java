package com.mySen.demo.dao;

import com.mySen.demo.model.Classfee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassfeeMapper {

    public int addClassfeeinfo(Classfee classfee);

    public int updateClassfeeofid(Classfee classfee);

    public List<Classfee> getAllclassfee(String major);

    public int delclassfee(int id);
}
