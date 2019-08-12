package com.airbnb.android.core;

import android.support.p000v4.util.ArrayMap;
import java.util.Map;

abstract class ClassRegistry {
    private static final Map<String, Class<?>> CLASS_MAP = new ArrayMap();

    ClassRegistry() {
    }

    protected static Class<?> maybeLoadClass(String className) {
        Class<?> klass = (Class) CLASS_MAP.get(className);
        if (klass != null) {
            return klass;
        }
        try {
            Class<?> klass2 = Class.forName(className);
            CLASS_MAP.put(className, klass2);
            return klass2;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
