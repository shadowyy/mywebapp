package com.shadow.util;

/**
 * 
 * Created by madness on 2016/11/16 0:08
 */
@SuppressWarnings("unchecked")
public class ConfigurableBeanCopier<F, T> extends BeanCopier<F, T> {

    public void setSourceClassName(String sourceClass) throws ClassNotFoundException {
        setSourceClass((Class<F>) Class.forName(sourceClass));
    }

    public void setTargetClassName(String targetClass) throws ClassNotFoundException {
        setTargetClass((Class<T>) Class.forName(targetClass));
    }

}