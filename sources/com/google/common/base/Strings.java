package com.google.common.base;

public final class Strings {
    public static String nullToEmpty(String string) {
        return string == null ? "" : string;
    }

    public static String emptyToNull(String string) {
        if (isNullOrEmpty(string)) {
            return null;
        }
        return string;
    }

    public static boolean isNullOrEmpty(String string) {
        return Platform.stringIsNullOrEmpty(string);
    }

    public static String repeat(String string, int count) {
        boolean z = true;
        Preconditions.checkNotNull(string);
        if (count <= 1) {
            if (count < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "invalid count: %s", count);
            return count == 0 ? "" : string;
        }
        int len = string.length();
        long longSize = ((long) len) * ((long) count);
        int size = (int) longSize;
        if (((long) size) != longSize) {
            throw new ArrayIndexOutOfBoundsException("Required array size too large: " + longSize);
        }
        char[] array = new char[size];
        string.getChars(0, len, array, 0);
        int n = len;
        while (n < size - n) {
            System.arraycopy(array, 0, array, n, n);
            n <<= 1;
        }
        System.arraycopy(array, 0, array, n, size - n);
        return new String(array);
    }
}
