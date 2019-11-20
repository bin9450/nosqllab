package com.nosqllab.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;

/**
 * @Author: Pan
 * @Date: 2019/7/5 14:24
 * @Description:
 **/
@Component
public class FileUtil {

    private final String filePath = "G:/datadoc/";
    public String addFile(MultipartFile fileUpload){

        DateUtil dul = new DateUtil();
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = dul.toName()+fileName+suffixName;
        //指定本地文件夹存储图片
        try {
            fileUpload.transferTo(new File(filePath+fileName));
            return filePath+fileName;
        } catch (Exception e) {
            return  "no";
        }
    }
}
