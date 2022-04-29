package com.mySen.demo.dao;

import com.mySen.demo.model.Local;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddLocalMapper {

    public int addLocal(Local local);
    public List<Local> seleclaAllLocal();

}
