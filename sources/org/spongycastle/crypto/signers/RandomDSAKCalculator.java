package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomDSAKCalculator implements DSAKCalculator {
    private static final BigInteger ZERO = BigInteger.valueOf(0);

    /* renamed from: q */
    private BigInteger f6888q;
    private SecureRandom random;

    public boolean isDeterministic() {
        return false;
    }

    public void init(BigInteger n, SecureRandom random2) {
        this.f6888q = n;
        this.random = random2;
    }

    public void init(BigInteger n, BigInteger d, byte[] message) {
        throw new IllegalStateException("Operation not supported");
    }

    public BigInteger nextK() {
        int qBitLength = this.f6888q.bitLength();
        while (true) {
            BigInteger k = new BigInteger(qBitLength, this.random);
            if (!k.equals(ZERO) && k.compareTo(this.f6888q) < 0) {
                return k;
            }
        }
    }
}
