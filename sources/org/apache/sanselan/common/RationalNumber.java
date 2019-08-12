package org.apache.sanselan.common;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class RationalNumber extends Number {

    /* renamed from: nf */
    private static final NumberFormat f6329nf = DecimalFormat.getInstance();
    public final int divisor;
    public final int numerator;

    public RationalNumber(int numerator2, int divisor2) {
        this.numerator = numerator2;
        this.divisor = divisor2;
    }

    public static final RationalNumber factoryMethod(long n, long d) {
        if (n > 2147483647L || n < -2147483648L || d > 2147483647L || d < -2147483648L) {
            while (true) {
                if ((n > 2147483647L || n < -2147483648L || d > 2147483647L || d < -2147483648L) && Math.abs(n) > 1 && Math.abs(d) > 1) {
                    n >>= 1;
                    d >>= 1;
                }
            }
            if (d == 0) {
                throw new NumberFormatException("Invalid value, numerator: " + n + ", divisor: " + d);
            }
        }
        long gcd = gcd(n, d);
        return new RationalNumber((int) (n / gcd), (int) (d / gcd));
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public RationalNumber negate() {
        return new RationalNumber(-this.numerator, this.divisor);
    }

    public double doubleValue() {
        return ((double) this.numerator) / ((double) this.divisor);
    }

    public float floatValue() {
        return ((float) this.numerator) / ((float) this.divisor);
    }

    public int intValue() {
        return this.numerator / this.divisor;
    }

    public long longValue() {
        return ((long) this.numerator) / ((long) this.divisor);
    }

    public String toString() {
        if (this.divisor == 0) {
            return "Invalid rational (" + this.numerator + "/" + this.divisor + ")";
        }
        if (this.numerator % this.divisor == 0) {
            return f6329nf.format((long) (this.numerator / this.divisor));
        }
        return this.numerator + "/" + this.divisor + " (" + f6329nf.format(((double) this.numerator) / ((double) this.divisor)) + ")";
    }
}
