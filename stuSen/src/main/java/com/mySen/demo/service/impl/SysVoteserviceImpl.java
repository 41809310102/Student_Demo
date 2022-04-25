package com.mySen.demo.service.impl;

import com.mySen.demo.dao.VoteMapper;
import com.mySen.demo.model.Vote;
import com.mySen.demo.service.IsVoteservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysVoteserviceImpl implements IsVoteservice {
    @Autowired
    private VoteMapper voteMapper;
    @Override
    public int addVoteinfo(Vote vote) {
        return voteMapper.addVoteinfo(vote);
    }

    @Override
    public List<Vote> selectVoteinfo(int id) {
        return voteMapper.selectVoteinfo(id);
    }

    @Override
    public List<Vote> selectVoteinfoall(String major) {
        return voteMapper.selectVoteinfoall(major);
    }

    @Override
    public int addA(int vid) {
        return 0;
    }

    @Override
    public int addB(int vid) {
        return 0;
    }

    @Override
    public int addC(int vid) {
        return 0;
    }

    @Override
    public int addD(int vid) {
        return 0;
    }
}
