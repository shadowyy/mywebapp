package com.shadow.util.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shadowyy
 * @version 2017/9/5 14:33
 */
public class BeanCreateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanCreateUtil.class);

    public static <T>T newClz(Class<T> clz) {
        T t = null;
        try {
            t = clz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("newInstance err:{}", e);
        }
        return t;
    }

}
