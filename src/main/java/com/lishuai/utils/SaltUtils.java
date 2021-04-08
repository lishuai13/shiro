package com.lishuai.utils;

import java.util.Random;


/**
 * 生成随机盐
 * @author lishuai
 */
public class SaltUtils {

    /**
     * 根据传入的整数 n 生成 n 位的随机字符串
     * @param n
     * @return
     */
    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }
}