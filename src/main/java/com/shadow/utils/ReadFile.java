package com.shadow.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by alice on 2016/12/2 20:37
 */
public class ReadFile {
    public static String toString(String filePath) {
        String fileName = getClassForStatic().getResource(filePath).getPath();
        String encoding = "UTF-8";
        File file = new File(fileName);
        long length = file.length();
        byte[] content = new byte[(int)length];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(content);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(content, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return "";
        }
    }

    private static final Class getClassForStatic() {
        return new Object() {
            public Class getClassForStatic() {
                return this.getClass();
            }
        }.getClassForStatic();
    }

}
