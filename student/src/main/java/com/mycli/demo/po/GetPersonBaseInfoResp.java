package com.mycli.demo.po;

import lombok.Data;

/**
 * @ClassName: GetPersonBaseInfoResp
 * @Description: TODO
 * @author: martin-wj
 * @createDate: 2020-12-21
 */
@Data
public class GetPersonBaseInfoResp {

    private String personName;

    private String gender;

    private String[] faceIds;
}
