package com.mySen.demo.dao;

import com.mySen.demo.model.Action;
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
public interface ActionMapper {

    public int addActioninfo(Action action);

    public List<Action> selectActiontbyid(int id);

    public List<Action> selectActiontall();

    public List<Action> selectActionbycheck(int id, String title);

    public int deleteAction(int id);

    public int getActionCounts(String title);

    public List<Action> getAllAction(String title,int pageStart,int pageSize);

    public int updateAction(Action action);

}
