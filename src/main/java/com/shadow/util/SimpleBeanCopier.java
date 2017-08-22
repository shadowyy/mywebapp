package com.shadow.util;

/**
 * Created by madness on 2016/11/16 0:09
 * 使用cglib做BeanCopier，并保留基本的扩展点
 */
public class SimpleBeanCopier<F, T> extends BeanCopier<F, T> {

    public SimpleBeanCopier(Class<F> sourceClass, Class<T> targetClass) {
        setSourceClass(sourceClass);
        setTargetClass(targetClass);
        init();
    }

}