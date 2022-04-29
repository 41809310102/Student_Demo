package com.mySen.demo.model;

import lombok.Data;

/**
 * Desc:分页逻辑
 * */
@Data
public class QueryInfo {
    private String query;
    private int pageNum=1;
    private int pageSize=1;
    private String value;
    private int id;
    }
