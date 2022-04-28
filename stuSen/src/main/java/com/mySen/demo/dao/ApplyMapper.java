package com.mySen.demo.dao;

import com.mySen.demo.model.Action;
import com.mySen.demo.model.Apply;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-04-21
 */
@Repository
public interface ApplyMapper {

    public  int addapply(Apply apply);

    public List<Apply> selectApplyofid(int id);

    public  int delApply(int id);

    public int  updatestate(int id,String state);

}
