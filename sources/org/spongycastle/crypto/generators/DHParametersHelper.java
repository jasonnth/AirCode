package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.math.p332ec.WNafUtil;
import org.spongycastle.util.BigIntegers;

class DHParametersHelper {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    DHParametersHelper() {
    }

    static BigInteger[] generateSafePrimes(int size, int certainty, SecureRandom random) {
        int qLength = size - 1;
        int minWeight = size >>> 2;
        while (true) {
            BigInteger q = new BigInteger(qLength, 2, random);
            BigInteger p = q.shiftLeft(1).add(ONE);
            if (p.isProbablePrime(certainty) && ((certainty <= 2 || q.isProbablePrime(certainty - 2)) && WNafUtil.getNafWeight(p) >= minWeight)) {
                return new BigInteger[]{p, q};
            }
        }
    }

    static BigInteger selectGenerator(BigInteger p, BigInteger q, SecureRandom random) {
        BigInteger g;
        BigInteger pMinusTwo = p.subtract(TWO);
        do {
            g = BigIntegers.createRandomInRange(TWO, pMinusTwo, random).modPow(TWO, p);
        } while (g.equals(ONE));
        return g;
    }
}
