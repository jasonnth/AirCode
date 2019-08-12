package org.spongycastle.pqc.math.ntru.euclid;

import java.math.BigInteger;

public class BigIntEuclidean {
    public BigInteger gcd;

    /* renamed from: x */
    public BigInteger f7234x;

    /* renamed from: y */
    public BigInteger f7235y;

    private BigIntEuclidean() {
    }

    public static BigIntEuclidean calculate(BigInteger a, BigInteger b) {
        BigInteger x = BigInteger.ZERO;
        BigInteger lastx = BigInteger.ONE;
        BigInteger y = BigInteger.ONE;
        BigInteger lasty = BigInteger.ZERO;
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger[] quotientAndRemainder = a.divideAndRemainder(b);
            BigInteger quotient = quotientAndRemainder[0];
            BigInteger bigInteger = a;
            a = b;
            b = quotientAndRemainder[1];
            BigInteger temp = x;
            x = lastx.subtract(quotient.multiply(x));
            lastx = temp;
            BigInteger temp2 = y;
            y = lasty.subtract(quotient.multiply(y));
            lasty = temp2;
        }
        BigIntEuclidean result = new BigIntEuclidean();
        result.f7234x = lastx;
        result.f7235y = lasty;
        result.gcd = a;
        return result;
    }
}
