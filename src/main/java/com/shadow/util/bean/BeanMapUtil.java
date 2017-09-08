package com.shadow.util.bean;

import net.sf.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shadowyy
 * @version 2017/9/8 10:41
 */
public class BeanMapUtil {
    /**
     * bean => Map
     */
    public static <T> Map<String, Object> toMap(T bean) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * Map => bean
     */
    public static <T> T toBean(Map<String, Object> map, Class<T> clz) {
        T bean = BeanCreateUtil.newClz(clz);
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * List<T> => List<Map<String, Object>>
     */
    public static <T> List<Map<String, Object>> toMapList(List<T> list) {
        List<Map<String, Object>> tmp = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            Map<String, Object> map = null;
            for (T t : list) {
                map = toMap(t);
                tmp.add(map);
            }
        }
        return tmp;
    }

    /**
     * List<Map<String, Object>> => List<T>
     */
    public static <T> List<T> toList(List<Map<String, Object>> list, Class<T> clz) {
        List<T> tmp = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (Map<String, Object> map : list) {
                tmp.add(toBean(map, clz));
            }
        }
        return tmp;
    }


}
