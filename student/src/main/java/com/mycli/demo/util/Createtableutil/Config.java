package com.mycli.demo.util.Createtableutil;

import lombok.Data;

@Data
public class Config {
    private int id;
    private String driverClassName="com.mysql.cj.jdbc.Driver";
    private String url="jdbc:mysql://localhost:3306/vaccines?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC" ;
    private String username="root";
    private String password="318422";
    }

