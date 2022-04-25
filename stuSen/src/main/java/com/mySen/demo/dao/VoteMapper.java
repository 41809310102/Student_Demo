package com.mySen.demo.dao;

import com.mySen.demo.model.Vote;
import org.springframework.stereotype.Repository;


@Repository
public interface VoteMapper {

   public  int addVoteinfo(Vote vote);
}
