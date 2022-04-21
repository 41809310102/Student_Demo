package com.mySen.demo.util.ercode;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Code {

    private int width;  //定义图片宽度
    private int hight; //定义图片高度
    private String format;  //定义图片格式
    private String content;//定义二维码内容
    private String type;//二维码的类型
    private int onColor;//二维码颜色
    private int offColor;//背景颜色

}
