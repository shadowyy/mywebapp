package com.shadow.util.json;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author shadowyy
 * @version 2017/9/5 15:09
 */
public class GsonUtil {
    private static final Gson GSON = new Gson();

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> clz) {
        return GSON.fromJson(json, clz);
    }

    public static <T> T fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    public static <T> T fromJson(String json, Type container, Type wrapped) {
        return GSON.fromJson(json, new GenericOf(container, new Type[]{wrapped}));
    }

    public static <T> T fromJson(String json, Type container, Type[] wrapped) {
        return GSON.fromJson(json, new GenericOf(container, wrapped));
    }

    public static <T> T fromJson(String json, Class<?> clz, Type wrapped) {
        return GSON.fromJson(json, new GenericOf(clz, new Type[]{wrapped}));
    }

    public static <T> T fromJson(String json, Class<?> clz, Type[] wrapped) {
        return GSON.fromJson(json, new GenericOf(clz, wrapped));
    }

    private static class GenericOf implements ParameterizedType {
        Type container;
        Type[] wrapped;

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
}
