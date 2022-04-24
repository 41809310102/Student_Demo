package com.mySen.demo.dao;

import com.mySen.demo.model.Award;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-04-21
 */
@Repository
public interface AwardMapper {

    public int addAwardinfo(Award award);

    public List<Award> selectAwardtbyid(int id);

    public List<Award> selectAwardbycheck(int id,String aname);

    public int uploadpic(int uid,String pic);

    public int updateAward(Award award);

    public int deleteAwerd(int id);

}
