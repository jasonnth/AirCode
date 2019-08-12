package com.airbnb.android.core.utils;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.Collection;

public final class Check {
    private static final String CLASSNAME = Check.class.getName();

    private Check() {
    }

    public static <T> T notNull(T item) {
        return notNull(item, null);
    }

    public static <T> T notNull(T item, String itemName) {
        if (item == null) {
            throwException(new NullPointerException(itemName));
        }
        return item;
    }

    public static void state(boolean condition) {
        state(condition, null);
    }

    public static void state(boolean condition, String message) {
        if (!condition) {
            throwException(new IllegalStateException(message));
        }
    }

    public static void argument(boolean condition) {
        argument(condition, null);
    }

    public static void argument(boolean condition, String message) {
        if (!condition) {
            throwException(new IllegalArgumentException(message));
        }
    }

    public static String notEmpty(String value) {
        return notEmpty(value, (String) null);
    }

    public static <T extends Collection<?>> T notEmpty(T value) {
        return notEmpty(value, (String) null);
    }

    public static <T extends Collection<?>> T notEmpty(T value, String message) {
        if (value == null || value.isEmpty()) {
            throwException(new IllegalStateException(message));
        }
        return value;
    }

    public static String notEmpty(String value, String message) {
        if (TextUtils.isEmpty(value)) {
            throwException(new IllegalStateException(message));
        }
        return value;
    }

    public static long validId(long value) {
        if (!IdUtils.isValidId(value)) {
            throwException(new IllegalStateException());
        }
        return value;
    }

    private static void throwException(RuntimeException t) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int topOfStack = 0;
        while (topOfStack < stackTrace.length && stackTrace[topOfStack].getClassName().equals(CLASSNAME)) {
            topOfStack++;
        }
        t.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, topOfStack, stackTrace.length - topOfStack));
        throw t;
    }
}
