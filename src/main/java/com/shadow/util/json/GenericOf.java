package com.shadow.util.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author yuyue
 * @version 2017-10-31 0031 17:14
 */
public class GenericOf implements ParameterizedType {
    private Type container;
    private Type[] wrapped;

    public GenericOf() {
    }

    public GenericOf(Type container, Type[] wrapped) {
        this.container = container;
        this.wrapped = wrapped;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return wrapped;
    }

    @Override
    public Type getRawType() {
        return container;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
