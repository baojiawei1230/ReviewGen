package com.alex.bao.ReviewGen.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * FileUploadUtil
 *
 * @Author Alex_Bao
 * @create 2018-08-18 create by IntelliJ IDEA
 */
public class FileUploadUtil {

    private static final List<String> ALLOW_TYPES = Arrays.asList(".jpg",".jpeg",".png",".ico");

    /**
     * rename picture
     *
     * @param fileName origin file name.
     * @return
     */
    public static String picReame(String fileName){
        String suffix = getSuffix(fileName);
        return System.currentTimeMillis() + "" + new Random().nextInt(99999999) + suffix;
    }

    /**
     * 判断图片类型是否属于上传类型
     *
     * @param subfix
     * @return
     */
    public static boolean isAllow(String subfix){
        return ALLOW_TYPES.contains(subfix);
    }

    /**
     *  get suffix of pictures
     *
     * @return
     */
    public static String getSuffix(String fileName) {
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index);
    }

}
