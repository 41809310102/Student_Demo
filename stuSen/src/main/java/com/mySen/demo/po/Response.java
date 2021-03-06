package com.mySen.demo.po;

import lombok.Data;

/**
 * @ClassName: Response
 * @Description: TODO 请求返回公共参数
 * @author: martin-wj
 * @createDate: 2020-12-21
 */
@Data
public class Response {

    private String requestId;

    private Error error;
}