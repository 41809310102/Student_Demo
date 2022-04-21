package com.mycli.demo.po;

import lombok.Data;

/**
 * @ClassName: VerifyFaceResp
 * @Description: TODO
 * @author: martin-wj
 * @createDate: 2020-12-21
 */
@Data
public class VerifyFaceResp extends Response {

    private String score;

    private Boolean isMatch;

}