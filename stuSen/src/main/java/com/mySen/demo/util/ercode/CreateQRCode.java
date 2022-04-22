package com.mySen.demo.util.ercode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.mySen.demo.util.OBSUtils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;

/*
* 二维码生成工具，整合华为云
* */
public class CreateQRCode {


    public   String createQRCode(Code code,String openid,String username,String time){
        //定义二维码的参数
        HashMap hashMap = new HashMap();
        hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");     //设置编码
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); //设置容错等级，等级越高，容量越小
        hashMap.put(EncodeHintType.MARGIN, 2);          //设置边距
        //生成二维码
        try {
            //生成矩阵
            MatrixToImageConfig config = new MatrixToImageConfig(code.getOnColor(), code.getOffColor());
            BitMatrix bitMatrix = new MultiFormatWriter().encode(code.getContent(), BarcodeFormat.QR_CODE,
                    code.getWidth(), code.getHight(), hashMap);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();

            String localpath = "F:/ercodepic/"+username+openid+"."+code.getFormat();
            Path file = new File(localpath).toPath();        //设置路径
            MatrixToImageWriter.writeToPath(bitMatrix, code.getFormat(), file,config);       //输出图像
            OBSUtils obs = new OBSUtils();//调用华为obs对象存储服务
            InputStream in = null;
            try {
                in = new FileInputStream(localpath);
                String key =username+openid+"."+code.getFormat();//以用户姓名+openid后5为key
                obs.ObsUpload("myfacepic",key,in);
                String link = obs.getLink(key);
                in.close();
                return link;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error:二维码生成错误！";
    }

}
