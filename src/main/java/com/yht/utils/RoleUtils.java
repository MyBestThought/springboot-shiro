package com.yht.utils;

import java.util.HashMap;
import java.util.Map;

public class RoleUtils {
    public static Map<Integer, String> map = new HashMap();
    static {
        map.put(0, "admin");
        map.put(1, "teacher");
        map.put(2, "student");
    }
    public static String getRoleByKey(int id){
        return map.get(id);
    }
    public static int getRoleByValue(String value){
        int id = 0;
        for(Map.Entry<Integer, String> entry : map.entrySet()){
            if(entry.getValue().equals(value)){
                id = entry.getKey();
                break;
            }
        }
        return id;
    }

}
