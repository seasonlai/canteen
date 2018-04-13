package com.season.utils;

import com.season.cons.CommonConstant;

import java.io.File;

/**
 * Created by season on 2018/4/14.
 */
public class FileUtil {

    public static String prepareWrite(String path) {
        //如果文件不为空，写入上传路径
        String rPath;
        if(File.separator.equals("/")){
            rPath = CommonConstant.BASE_PATH_LINUX;
        }else
            rPath=CommonConstant.BASE_PATH_WIN;

        File file = new File(rPath,path);
        if(!file.exists()){
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

}
