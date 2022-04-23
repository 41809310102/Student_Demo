package com.mySen.demo.service;

import com.mySen.demo.model.Subject;
import com.mySen.demo.model.User;

import java.util.List;

public interface IsSubjectservice {
    /**
     * 添加用户信息
     *
     * @param user 用户信息
     * @return 数据库处理
     */

    public int addSubjectinfo(Subject sub);

    public List<Subject> selectSubjectbyid(int id);

    public List<Subject> selectSubjectbyid(int id,String type,String xid);
}
