package com.nytimes.android.external.cache;

public final class Objects {
    public static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }
}
