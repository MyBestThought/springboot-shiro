package com.yht.utils;

import java.util.Random;

public class ShiroUtils {
    public static String generateSaltString(int n){
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()_+-=[]|:";
        char[] ches = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            char ch = ches[new Random().nextInt(ches.length)];
            sb.append(ch);
        }
        return sb.toString();
    }

}
