package com.shadow.util.string;

/**
 * @author shadowyy
 * @version 2017/8/22 14:28
 */
public class StringUtil {
    public static String lowerFirstLetter(String s) {
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    public static String upperFirstLetter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
