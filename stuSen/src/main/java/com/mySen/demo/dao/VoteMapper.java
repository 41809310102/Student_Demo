package com.mySen.demo.dao;

import com.mySen.demo.model.Vote;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VoteMapper {

   public  int addVoteinfo(Vote vote);

   public List<Vote> selectVoteinfo(int id);

   public List<Vote> selectVoteinfoall(String major);

   public int addA(int vid);

   public int addB(int vid);

   public int addC(int vid);

   public int addD(int vid);

}
