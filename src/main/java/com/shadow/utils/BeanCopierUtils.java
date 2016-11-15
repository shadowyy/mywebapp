package com.shadow.utils;

/**
 * Created by alice on 2016/11/15 13:18
 */
public class BeanCopierUtils<S,T>{
    private net.sf.cglib.beans.BeanCopier beanCopier;

    private Class<S> sourceClass;

    private Class<T> targetClass;

    public T copy(S s) {
        try {
            T t = targetClass.newInstance();
            beanCopier.copy(s, t, null);
            return afterCopy(s, t);
        } catch (Exception e) {
            throw new RuntimeException("create object fail, class:" + targetClass.getName() + " ", e);
        }
    }

    public T afterCopy(S source, T target){
        return target;
    }

}
