package com.mySen.demo.po;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName: CreatePerson
 * @Description: TODO 创建人员请求参数
 * @author: martin-wj
 * @createDate: 2020-12-21
 */
@Data
@Builder
public class CreatePerson {
    private String groupId;

    private String personName;

    private String personId;

    private Integer gender;

    private String  personExDescription01;

    private String  personExDescription02;

    private String  personExDescription03;

    private String image;

    private String url;
}
