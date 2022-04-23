package com.mySen.demo.dao;

import com.mySen.demo.model.Subject;
import com.mySen.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-04-21
 */
@Repository
public interface SubjectMapper {

    public int addSubjectinfo(Subject sub);

    public List<Subject> selectSubjectbyid(int id);

    public List<Subject> selectSubjectbycheck(int id,String type,String xid);

}
