package com.google.common.primitives;

public final class Booleans {
    public static int compare(boolean a, boolean b) {
        if (a == b) {
            return 0;
        }
        return a ? 1 : -1;
    }
}
