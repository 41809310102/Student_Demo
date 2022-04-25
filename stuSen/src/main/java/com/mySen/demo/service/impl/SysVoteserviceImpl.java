package com.mySen.demo.service.impl;

import com.mySen.demo.dao.VoteMapper;
import com.mySen.demo.model.Vote;
import com.mySen.demo.service.IsVoteservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysVoteserviceImpl implements IsVoteservice {
    @Autowired
    private VoteMapper voteMapper;
    @Override
    public int addVoteinfo(Vote vote) {
        return voteMapper.addVoteinfo(vote);
    }
}
