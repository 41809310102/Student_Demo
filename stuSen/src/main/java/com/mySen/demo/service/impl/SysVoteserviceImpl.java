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
    public List<Vote> selectVoteinfoall(String major,int uid) {
        return voteMapper.selectVoteinfoall(major,uid);
    }

    @Override
    public List<Vote> selectVoteinfolog(String major, int uid) {
        return voteMapper.selectVoteinfolog(major,uid);
    }

    @Override
    public int addA(int vid) {
        return voteMapper.addA(vid);
    }

    @Override
    public int addB(int vid) {
        return voteMapper.addB(vid);
    }

    @Override
    public int addC(int vid) {
        return voteMapper.addC(vid);
    }

    @Override
    public int addD(int vid) {
        return voteMapper.addD(vid);
    }
}
