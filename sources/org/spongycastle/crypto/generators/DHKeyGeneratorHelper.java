package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.math.p332ec.WNafUtil;
import org.spongycastle.util.BigIntegers;

class DHKeyGeneratorHelper {
    static final DHKeyGeneratorHelper INSTANCE = new DHKeyGeneratorHelper();
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    private DHKeyGeneratorHelper() {
    }

    /* access modifiers changed from: 0000 */
    public BigInteger calculatePrivate(DHParameters dhParams, SecureRandom random) {
        BigInteger x;
        int limit = dhParams.getL();
        if (limit != 0) {
            int minWeight = limit >>> 2;
            do {
                x = new BigInteger(limit, random).setBit(limit - 1);
            } while (WNafUtil.getNafWeight(x) < minWeight);
        } else {
            BigInteger min = TWO;
            int m = dhParams.getM();
            if (m != 0) {
                min = ONE.shiftLeft(m - 1);
            }
            BigInteger q = dhParams.getQ();
            if (q == null) {
                q = dhParams.getP();
            }
            BigInteger max = q.subtract(TWO);
            int minWeight2 = max.bitLength() >>> 2;
            do {
                x = BigIntegers.createRandomInRange(min, max, random);
            } while (WNafUtil.getNafWeight(x) < minWeight2);
        }
        return x;
    }

    /* access modifiers changed from: 0000 */
    public BigInteger calculatePublic(DHParameters dhParams, BigInteger x) {
        return dhParams.getG().modPow(x, dhParams.getP());
    }
}
