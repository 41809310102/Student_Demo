package com.mySen.demo.dao;

import com.mySen.demo.model.Question;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper {

    public int addquestion(Question question);

    public List<Question> getQuestionofid(String questid);

}
