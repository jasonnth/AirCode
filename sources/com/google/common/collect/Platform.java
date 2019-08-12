package com.google.common.collect;

import java.lang.reflect.Array;

final class Platform {
    static <T> T[] newArray(T[] reference, int length) {
        return (Object[]) Array.newInstance(reference.getClass().getComponentType(), length);
    }
}
