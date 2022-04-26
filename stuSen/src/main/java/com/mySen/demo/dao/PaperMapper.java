package com.mySen.demo.dao;

import com.mySen.demo.model.Paper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperMapper {

    public int addPaper(Paper paper);

    public List<Paper> getPaper(String major);
}
