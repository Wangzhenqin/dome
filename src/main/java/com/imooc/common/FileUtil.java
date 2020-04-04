package com.imooc.common;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Calendar;

public class FileUtil {
    private static Object lockObj = new Object();
    private static final String BASE_PATH = "/data/nfs/fingo";
    private static final String READ_BASE_PATH = "/data/nfs";
    private static final String URL_ROOT = "/fingo";

    public static String getWriteFilePath(String module, String ext) {
        return getWriteFilePath(module, "", ext);
    }

    /**
     * @param module
     * @param ext 扩展名
     * @param prefix 文件名前缀
     * @return
     */
    public static String getWriteFilePath(String module, String prefix, String ext){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dirPath = String.format("%s/%s/%d-%02d/%02d", BASE_PATH, module, year, month + 1, day);
        File dirFile = new File(dirPath);
        synchronized (lockObj){
            if(!dirFile.exists()){
                dirFile.mkdirs();
            }
        }
        if(!StringUtils.isBlank(prefix)){
            return String.format("%s/%s-%d.%s", dirPath, prefix, System.nanoTime(), ext);
        }
        return String.format("%s/%d.%s", dirPath, System.nanoTime(), ext);
    }

    /**
     * @param module
     * @return
     */
    public static String getOveridableFileWritePath(String module, String fileName){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dirPath = String.format("%s/%s/%d-%2d/%02d", BASE_PATH, module, year, month + 1, day);
        File dirFile = new File(dirPath);
        synchronized (lockObj){
            if(!dirFile.exists()){
                dirFile.mkdirs();
            }
        }
        if(StringUtils.isBlank(fileName)){
            fileName = String.valueOf(System.nanoTime());
        }
        return String.format("%s/%s", dirPath, fileName);
    }
    /**
     * 根据文件路径生成下载地址
     * @param path
     * @return
     */
    public static String getDownloadFileUrl(String path){
        return URL_ROOT + path.substring(BASE_PATH.length()) ;
    }

    public static String getFilePathFromUrl(String url){
        if(url.startsWith("/s3")){
            return READ_BASE_PATH + "/s3/fingo" + url.substring(3);
        }
        return READ_BASE_PATH + url;
    }
}
