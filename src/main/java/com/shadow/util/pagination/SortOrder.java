package com.shadow.util.pagination;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shadowyy
 * @version 2017/11/3 22:42
 */
public enum SortOrder {

    ASC("asc"), DESC("desc");

    private final String order;

    SortOrder(String order) {
        this.order = order;
    }

    private static Map<String, SortOrder> FORMAT_MAP;

    static {
        FORMAT_MAP = new HashMap<>();
        for (SortOrder sortOrder : SortOrder.values()) {
            FORMAT_MAP.put(sortOrder.getOrder(), sortOrder);
        }
    }

    @JsonCreator
    public static SortOrder fromString(String string) {
        SortOrder sortOrder = FORMAT_MAP.get(string);
        if (sortOrder == null) {
            throw new IllegalArgumentException(string + "has no value");
        }
        return sortOrder;
    }

    @JsonValue
    public String toValue() {
        for (Map.Entry<String, SortOrder> entry : FORMAT_MAP.entrySet()) {
            if (entry.getValue() == this) {
                return entry.getKey();
            }
        }
        return null;
    }


    public String getOrder() {
        return order;
    }

}
