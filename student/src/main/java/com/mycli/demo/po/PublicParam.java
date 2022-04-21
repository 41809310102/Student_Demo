package com.mycli.demo.po;

import lombok.Data;

/**
 * @ClassName: PublicParam
 * @Description: TODO 通用公共请求参数
 * @author: martin-wj
 * @createDate: 2020-12-21
 */
@Data
public class PublicParam {

    private String action;

    private String version;

    private String region;

}
