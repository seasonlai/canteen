package com.season.utils;

import com.season.cons.CommonConstant;

import java.io.File;

/**
 * Created by season on 2018/4/14.
 */
public class MyFileUtil {

    public static String prepareWrite(String path) {
        //如果文件不为空，写入上传路径
        File file = new File(getBasePath(), path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static String getServerFilePath(String path) {
        return getServerFile(path).getAbsolutePath();
    }

    public static File getServerFile(String path) {
        return new File(getBasePath(),path);
    }


    public static String getBasePath() {
        return File.separator.equals("/") ?
                CommonConstant.BASE_PATH_LINUX : CommonConstant.BASE_PATH_WIN;
    }
}
