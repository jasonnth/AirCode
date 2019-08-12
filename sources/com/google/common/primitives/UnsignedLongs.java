package com.google.common.primitives;

import com.facebook.appevents.AppEventsConstants;
import com.google.common.base.Preconditions;
import java.math.BigInteger;

public final class UnsignedLongs {
    private static final int[] maxSafeDigits = new int[37];
    private static final long[] maxValueDivs = new long[37];
    private static final int[] maxValueMods = new int[37];

    private static long flip(long a) {
        return Long.MIN_VALUE ^ a;
    }

    public static int compare(long a, long b) {
        return Longs.compare(flip(a), flip(b));
    }

    public static long divide(long dividend, long divisor) {
        int i = 1;
        if (divisor < 0) {
            if (compare(dividend, divisor) < 0) {
                return 0;
            }
            return 1;
        } else if (dividend >= 0) {
            return dividend / divisor;
        } else {
            long quotient = ((dividend >>> 1) / divisor) << 1;
            if (compare(dividend - (quotient * divisor), divisor) < 0) {
                i = 0;
            }
            return ((long) i) + quotient;
        }
    }

    public static long remainder(long dividend, long divisor) {
        if (divisor < 0) {
            if (compare(dividend, divisor) < 0) {
                return dividend;
            }
            return dividend - divisor;
        } else if (dividend >= 0) {
            return dividend % divisor;
        } else {
            long rem = dividend - ((((dividend >>> 1) / divisor) << 1) * divisor);
            if (compare(rem, divisor) < 0) {
                divisor = 0;
            }
            return rem - divisor;
        }
    }

    public static String toString(long x) {
        return toString(x, 10);
    }

    public static String toString(long x, int radix) {
        long quotient;
        Preconditions.checkArgument(radix >= 2 && radix <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", radix);
        if (x == 0) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        if (x > 0) {
            return Long.toString(x, radix);
        }
        char[] buf = new char[64];
        int i = buf.length;
        if (((radix - 1) & radix) == 0) {
            int shift = Integer.numberOfTrailingZeros(radix);
            int mask = radix - 1;
            do {
                i--;
                buf[i] = Character.forDigit(((int) x) & mask, radix);
                x >>>= shift;
            } while (x != 0);
        } else {
            if ((radix & 1) == 0) {
                quotient = (x >>> 1) / ((long) (radix >>> 1));
            } else {
                quotient = divide(x, (long) radix);
            }
            i--;
            buf[i] = Character.forDigit((int) (x - (((long) radix) * quotient)), radix);
            long x2 = quotient;
            while (x2 > 0) {
                i--;
                buf[i] = Character.forDigit((int) (x2 % ((long) radix)), radix);
                x2 /= (long) radix;
            }
        }
        return new String(buf, i, buf.length - i);
    }

    static {
        BigInteger overflow = new BigInteger("10000000000000000", 16);
        for (int i = 2; i <= 36; i++) {
            maxValueDivs[i] = divide(-1, (long) i);
            maxValueMods[i] = (int) remainder(-1, (long) i);
            maxSafeDigits[i] = overflow.toString(i).length() - 1;
        }
    }
}
