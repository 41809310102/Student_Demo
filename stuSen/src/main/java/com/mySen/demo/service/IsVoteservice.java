package com.mySen.demo.service;

import com.mySen.demo.model.Vote;

import java.util.List;

public interface IsVoteservice {

    public  int addVoteinfo(Vote vote);

    public List<Vote> selectVoteinfo(int id);

    public List<Vote> selectVoteinfoall(String major);

    public int addA(int vid);

    public int addB(int vid);

    public int addC(int vid);

    public int addD(int vid);
}
