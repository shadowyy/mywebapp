package com.shadow.utils;

/**
 * Created by madness on 2016/11/16 0:08
 */
public class ConfigurableBeanCopier<F, T> extends BeanCopier<F, T> {

    public void setTargetClassName(String targetClass) throws ClassNotFoundException {
        setTargetClass((Class<T>) Class.forName(targetClass));
    }

    public void setSourceClassName(String sourceClass) throws ClassNotFoundException {
        setSourceClass((Class<F>) Class.forName(sourceClass));
    }

}