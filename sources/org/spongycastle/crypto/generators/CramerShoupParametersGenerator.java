package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.params.CramerShoupParameters;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.util.BigIntegers;

public class CramerShoupParametersGenerator {
    /* access modifiers changed from: private */
    public static final BigInteger ONE = BigInteger.valueOf(1);
    private int certainty;
    private SecureRandom random;
    private int size;

    private static class ParametersHelper {
        private static final BigInteger TWO = BigInteger.valueOf(2);

        private ParametersHelper() {
        }

        static BigInteger[] generateSafePrimes(int size, int certainty, SecureRandom random) {
            BigInteger q;
            BigInteger p;
            int qLength = size - 1;
            while (true) {
                q = new BigInteger(qLength, 2, random);
                p = q.shiftLeft(1).add(CramerShoupParametersGenerator.ONE);
                if (!p.isProbablePrime(certainty) || (certainty > 2 && !q.isProbablePrime(certainty))) {
                }
            }
            return new BigInteger[]{p, q};
        }

        static BigInteger selectGenerator(BigInteger p, SecureRandom random) {
            BigInteger g;
            BigInteger pMinusTwo = p.subtract(TWO);
            do {
                g = BigIntegers.createRandomInRange(TWO, pMinusTwo, random).modPow(TWO, p);
            } while (g.equals(CramerShoupParametersGenerator.ONE));
            return g;
        }
    }

    public void init(int size2, int certainty2, SecureRandom random2) {
        this.size = size2;
        this.certainty = certainty2;
        this.random = random2;
    }

    public CramerShoupParameters generateParameters() {
        BigInteger q = ParametersHelper.generateSafePrimes(this.size, this.certainty, this.random)[1];
        BigInteger g1 = ParametersHelper.selectGenerator(q, this.random);
        BigInteger g2 = ParametersHelper.selectGenerator(q, this.random);
        while (g1.equals(g2)) {
            g2 = ParametersHelper.selectGenerator(q, this.random);
        }
        return new CramerShoupParameters(q, g1, g2, new SHA256Digest());
    }

    public CramerShoupParameters generateParameters(DHParameters dhParams) {
        BigInteger p = dhParams.getP();
        BigInteger g1 = dhParams.getG();
        BigInteger g2 = ParametersHelper.selectGenerator(p, this.random);
        while (g1.equals(g2)) {
            g2 = ParametersHelper.selectGenerator(p, this.random);
        }
        return new CramerShoupParameters(p, g1, g2, new SHA256Digest());
    }
}
