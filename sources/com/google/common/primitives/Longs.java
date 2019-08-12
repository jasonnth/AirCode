package com.google.common.primitives;

import java.util.Arrays;

public final class Longs {
    private static final byte[] asciiDigits = createAsciiDigits();

    public static int compare(long a, long b) {
        if (a < b) {
            return -1;
        }
        return a > b ? 1 : 0;
    }

    private static byte[] createAsciiDigits() {
        byte[] result = new byte[128];
        Arrays.fill(result, -1);
        for (int i = 0; i <= 9; i++) {
            result[i + 48] = (byte) i;
        }
        for (int i2 = 0; i2 <= 26; i2++) {
            result[i2 + 65] = (byte) (i2 + 10);
            result[i2 + 97] = (byte) (i2 + 10);
        }
        return result;
    }
}
