package com.airbnb.android.lib.utils;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.List;

public class PrimitiveArrayHelper<T> extends AbstractList<T> {
    private final T[] data;

    private PrimitiveArrayHelper(T[] data2) {
        this.data = data2;
    }

    public static <T> List<T> toIntegerList(int... data2) {
        return new PrimitiveArrayHelper(toBoxedArray(Integer.class, data2));
    }

    private static <T> T[] toBoxedArray(Class<T> boxClass, Object components) {
        int length = components != null ? Array.getLength(components) : 0;
        Object res = Array.newInstance(boxClass, length);
        for (int i = 0; i < length; i++) {
            Array.set(res, i, Array.get(components, i));
        }
        return (Object[]) res;
    }

    public T get(int index) {
        return this.data[index];
    }

    public int size() {
        return this.data.length;
    }
}
