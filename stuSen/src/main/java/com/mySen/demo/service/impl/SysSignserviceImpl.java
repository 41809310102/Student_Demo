package com.mySen.demo.service.impl;

import com.mySen.demo.dao.SignMapper;
import com.mySen.demo.dao.UserMapper;
import com.mySen.demo.model.Sign;
import com.mySen.demo.model.User;
import com.mySen.demo.service.IsSignservice;
import com.mySen.demo.service.IsUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysSignserviceImpl implements IsSignservice {
   @Autowired
   private SignMapper signMapper;
    @Override
    public int addSign(Sign sign) {
        return signMapper.addSigninfo(sign);
    }

    @Override
    public List<Sign> selectSign(int uid) {
        return signMapper.selectSign(uid);
    }
}
