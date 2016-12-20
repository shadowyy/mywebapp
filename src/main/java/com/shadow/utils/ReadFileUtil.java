package com.shadow.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by alice on 2016/12/2 20:37
 */
public class ReadFileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadFileUtil.class);

    public static String toString(String filePath) {
        File file = new File(getClassForStatic().getResource(filePath).getPath());
        Long len = file.length();
        byte[] byteContent = new byte[len.intValue()];

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            in.read(byteContent);
            in.close();
            return new String(byteContent, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("ReadFileUtil FileInputStream error:" + e);
            return null;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                LOGGER.error("ReadFileUtil close error:" + e);
            }
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
