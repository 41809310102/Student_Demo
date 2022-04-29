package com.mySen.demo.model;

import lombok.Data;

/**
 * Desc:活动签到码类
 * */
@Data
public class Codelocal {
    private String lat2; //经纬度
    private String lng2;//经纬度
    private int actionid;//活动id
}
