package com.shadow.util;

import com.shadow.util.bean.BeanCreateUtil;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 1.名称和属性相同 2.源和目标都必须要有get和set方法
 * TODO 可能需要字段映射
 *
 * @author shadowyy
 * @version 2017/9/5 14:20
 */
public class BeanCopyUtil {
    private static final ConcurrentMap<String, BeanCopier> map = new ConcurrentHashMap<>();

    private static BeanCopier getBeanCopier(Object source, Object target, boolean useConverter) {
        final String key = source.getClass().getName() + target.getClass().getName()+useConverter;
        BeanCopier beanCopier = map.get(key);
        if (beanCopier == null) {
            beanCopier = BeanCopier.create(source.getClass(), target.getClass(), useConverter);
            BeanCopier copier = map.putIfAbsent(key, beanCopier);
            if (copier != null) {
                beanCopier = copier;
            }
        }
        return beanCopier;
    }

    public static <F, T> T copy(F source, Class<T> clz) {
        T target = BeanCreateUtil.newClz(clz);
        getBeanCopier(source, target, false).copy(source, target, null);
        return target;
    }

    public static <F, T> T copy(F source, Class<T> clz, Converter converter) {
        T target = BeanCreateUtil.newClz(clz);
        getBeanCopier(source, BeanCreateUtil.newClz(clz), true).copy(source, target, converter);
        return target;
    }

}